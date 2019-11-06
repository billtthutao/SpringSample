package cadt.rtc;

import java.net.URI;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.springframework.stereotype.Component;

import com.ibm.team.process.client.IProcessClientService;
import com.ibm.team.process.common.IProjectArea;
import com.ibm.team.repository.client.ITeamRepository;
import com.ibm.team.repository.client.TeamPlatform;
import com.ibm.team.repository.client.ITeamRepository.ILoginHandler;
import com.ibm.team.repository.client.ITeamRepository.ILoginHandler.ILoginInfo;
import com.ibm.team.repository.common.TeamRepositoryException;
import com.ibm.team.workitem.client.IAuditableClient;
import com.ibm.team.workitem.client.IDetailedStatus;
import com.ibm.team.workitem.client.IQueryClient;
import com.ibm.team.workitem.client.IWorkItemClient;
import com.ibm.team.workitem.client.IWorkItemWorkingCopyManager;
import com.ibm.team.workitem.client.WorkItemWorkingCopy;
import com.ibm.team.workitem.common.IAuditableCommon;
import com.ibm.team.workitem.common.IWorkItemCommon;
import com.ibm.team.workitem.common.expression.AttributeExpression;
import com.ibm.team.workitem.common.expression.IQueryableAttribute;
import com.ibm.team.workitem.common.expression.IQueryableAttributeFactory;
import com.ibm.team.workitem.common.expression.QueryableAttributes;
import com.ibm.team.workitem.common.expression.Term;
import com.ibm.team.workitem.common.expression.Term.Operator;
import com.ibm.team.workitem.common.model.AttributeOperation;
import com.ibm.team.workitem.common.model.IAttribute;
import com.ibm.team.workitem.common.model.IWorkItem;
import com.ibm.team.workitem.common.model.Identifier;
import com.ibm.team.workitem.common.query.IQueryResult;
import com.ibm.team.workitem.common.query.IResolvedResult;
import com.ibm.team.workitem.common.workflow.IWorkflowAction;
import com.ibm.team.workitem.common.workflow.IWorkflowInfo;

import cadt.domain.Model;
import cadt.domain.WorkItem;
import cadt.domain.WorkItemType;

@Component
public class CepsRtcFactory implements RtcFactory{

	private  static LoginHandler loginHandler;
	private  static ITeamRepository teamRepository;
	private  static IProcessClientService processClient;
	private  static IAuditableClient auditableClient;
	private  static IProjectArea projectArea;
	
	public CepsRtcFactory() throws TeamRepositoryException {
		
		loginHandler = new LoginHandler("xxxxxxxx","xxxxxxxxxxxxx");
		TeamPlatform.startup();
		teamRepository = TeamPlatform.getTeamRepositoryService().getTeamRepository("https://xxxxxxxxxxx");
		teamRepository.registerLoginHandler(loginHandler);
		
		teamRepository.login(null);
		
		processClient = (IProcessClientService) teamRepository.getClientLibrary(IProcessClientService.class);
		auditableClient = (IAuditableClient) teamRepository.getClientLibrary(IAuditableClient.class);
		
		URI uri= URI.create("CEPS - Change Management".replaceAll(" ", "%20"));
		projectArea = (IProjectArea) processClient.findProcessArea(uri, null, null);
	
	}
	
	@Override
	public HashSet<WorkItem> fetchWorkItemByStatus(String status) {
			
		try {
			IQueryClient queryClient= (IQueryClient) teamRepository.getClientLibrary(IQueryClient.class);
			
			IProgressMonitor monitor = new NullProgressMonitor();
			
			IQueryableAttributeFactory factory = QueryableAttributes.getFactory(IWorkItem.ITEM_TYPE);
			
			
			IQueryableAttribute workItemAttribute = factory.findAttribute(projectArea,IWorkItem.STATE_PROPERTY,auditableClient, monitor);
	
			AttributeExpression storyExpression = null;
			storyExpression = new AttributeExpression(workItemAttribute,AttributeOperation.EQUALS,"CEPS_Story_Workflow.state.s1"); 
			AttributeExpression ptrExpression = null;
			ptrExpression = new AttributeExpression(workItemAttribute,AttributeOperation.EQUALS,"CEPS_Story_Workflow.state.s23"); 
			
			Term term = new Term(Operator.OR);
			term.add(storyExpression);
			term.add(ptrExpression);
			
			IQueryResult<IResolvedResult<IWorkItem>> result=queryClient.getResolvedExpressionResults(projectArea,term,IWorkItem.FULL_PROFILE);
			
			HashSet<WorkItem> workitem_list = new HashSet<WorkItem>();
			
			while (result.hasNext(monitor)) {
				IWorkItem wi=result.next(monitor).getItem();
				WorkItem w = formatIWorkItem(wi);
				if(w != null)
				workitem_list.add(w);
			}
			
			return workitem_list;
		}catch(TeamRepositoryException e) {
			//return empty workitem list
			return new HashSet<WorkItem>();
		}
	}

	public WorkItem fetchWorkItemById(String id) {
		
		try {
			IWorkItemClient workItemClient = (IWorkItemClient) teamRepository.getClientLibrary(IWorkItemClient.class);
			
			int workItemNo = new Integer(id).intValue();
			IWorkItem workItem = workItemClient.findWorkItemById(workItemNo, IWorkItem.FULL_PROFILE, null);
			
			if(workItem == null)
				return null;
			else
				return formatIWorkItem(workItem);
		}catch(TeamRepositoryException e) {
			return null;
		}
	}
	
	public int updateWorkItemStatus(String id, String newState) {
		
		try {
			int workItemNo = new Integer(id).intValue();
			IWorkItemClient workItemClient = (IWorkItemClient) teamRepository.getClientLibrary(IWorkItemClient.class);
			IWorkItem workItem = workItemClient.findWorkItemById(workItemNo, IWorkItem.SMALL_PROFILE, null);
			IWorkItemWorkingCopyManager wcm = workItemClient.getWorkItemWorkingCopyManager();
			wcm.connect(workItem, IWorkItem.FULL_PROFILE, null);
			
			WorkItemWorkingCopy wc = wcm.getWorkingCopy(workItem);
			IWorkflowInfo workflowInfo = workItemClient.findWorkflowInfo(workItem, null);
			Identifier<IWorkflowAction>[] actionIds = workflowInfo.getActionIds(workItem.getState2());
			
			for (Identifier<IWorkflowAction> action : actionIds){
				String actionName = workflowInfo.getActionName(action);
				if (actionName != null && actionName.equalsIgnoreCase(newState)) {
					String stringIdentifier = action.getStringIdentifier();
					wc.setWorkflowAction(stringIdentifier);
					break;
				}
			}
			
			IDetailedStatus s = wc.save(null);
			if (!s.isOK()) {
				return -1;
			}
			
			wcm.disconnect(workItem);
			
			return 0;
		}catch(TeamRepositoryException e) {
			return -1;
		}
	}
	
	private WorkItem formatIWorkItem(IWorkItem wi) throws TeamRepositoryException{
		String scope = "sit";
		IProgressMonitor monitor = new NullProgressMonitor();
		IAuditableCommon paramIAuditableCommon = (IAuditableCommon) teamRepository.getClientLibrary(IAuditableCommon.class);
		IWorkItemCommon workItemCommon = (IWorkItemCommon) teamRepository.getClientLibrary(IWorkItemCommon.class);
		String[] attribute_group={"internalState",
								  "summary",
								  "workItemType",
				  				  "z-jcl-ATTID",
				  				  "z-procs-ATTID",
				  				  "z-cards-ATTID",
				  				  "z-sit-setup-instructions-ATTID",
				  				  "z-production-setup-instructions-ATTID",
				  				  "z-adm-teram-ATTID",
				  				  "z-uat-setup-instructions-ATTID",
				  				  "z-dem-modules",
				  				  };
		IAttribute attribute = null;
		
		//get work item type
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[2], monitor);
		WorkItemType workItemType = null;
		if(attribute != null){
			String witype = (String)attribute.getValue(paramIAuditableCommon, wi, monitor);
			workItemType = WorkItemType.getWorkItemType(witype);
		}
		
		//pick out those story belong to backend and all PTR
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[8], monitor);
		Identifier x = (Identifier) attribute.getValue(paramIAuditableCommon, wi, monitor);
		if(!x.getStringIdentifier().equals("z-adm-team-ENUM.literal.l8") && workItemType != WorkItemType.PTR){
			return null;
		}
		//get workItemNo
		//attribute = workItemCommon.findAttribute(RtcHelper.getProjectArea(), attribute_group[0], monitor);
		//String workItemNo = (String)attribute.getValue(paramIAuditableCommon, wi, monitor);
		String workItemNo = wi.getId()+"";
		
		//set work item status
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[0], monitor);
		String workItemStatus = null;
		if(attribute != null){
			if(((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).equals("CEPS_Story_Workflow.state.s23") ||
				((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).equals("ptr sit approved status code")){
				workItemStatus = "C47";
			}
			else if(((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).equals("CEPS_Story_Workflow.state.s13") ||
					((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).equals("ptr prod approved status code")){
				workItemStatus = "C85";
			}else if(((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).equals("CEPS_Story_Workflow.state.s20")){
				workItemStatus = "C60";
			}else{     //get undefined workitem status from WorkItem
				//workItemStatus = WorkItemStatus.getWorkItemStatus((String)attribute.getValue(paramIAuditableCommon, wi, monitor));
				workItemStatus = (String)attribute.getValue(paramIAuditableCommon, wi, monitor);
			}
		}
		
		//get work item summary
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[1], monitor);
		String workItemTitle = null;
		if(attribute != null){
			workItemTitle = (String)attribute.getValue(paramIAuditableCommon, wi, monitor);
		}
		
		//generate model list
		HashSet<Model> model_list= new HashSet<Model>();
		//JCL
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[3], monitor);
		if(attribute != null){
			String[] jcls = ((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).split(" ");
			for(String jcl:jcls){
				if(jcl.length() > 3 ){
						model_list.add(new Model(workItemNo, "JCL",jcl.trim()));
				}
			}
		}
		
		//PROC
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[4], monitor);
		if(attribute != null){
			String[] procs = ((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).split(" ");
			for(String proc:procs){
				if(proc.length() > 3  ){
					model_list.add(new Model(workItemNo, "PROC",proc.trim()));
				}
			}
		}
		
		//CARD
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[5], monitor);
		if(attribute != null){
			String[] cards = ((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).split(" ");
			for(String card:cards){
				if(card.length() > 3 ){
					model_list.add(new Model(workItemNo, "CARD",card.trim()));
				}
			}
		}
		
		//SETUP & DDL JOB
		String job_match = ""; 
		//sit setupjob
		if("sit".equalsIgnoreCase(scope)){
			attribute = workItemCommon.findAttribute(projectArea, attribute_group[6], monitor);
			job_match="BILLHU.WW.FTEST.JCLLIB";
		}else if("prod".equalsIgnoreCase(scope)){
			attribute = workItemCommon.findAttribute(projectArea, attribute_group[7], monitor);
			job_match="NEDDBA2.PROD.JCLLIB.SETUP";
		}else if("uat".equalsIgnoreCase(scope)){
			attribute = null;  //no uat setup job
		}
		
		if(attribute != null){
			String value = (String) attribute.getValue(paramIAuditableCommon, wi, monitor);
			String[] tmp = value.split(" ");
		
			for(String s:tmp){
				int pos1;
				if((pos1=s.indexOf(job_match)) != -1){
					int pos2 =s.indexOf(")");
					int pos3 = s.indexOf("(");
					if(s.substring(pos3+1,pos2).startsWith("NWWSET")){
						model_list.add(new Model(workItemNo,"SETUPJOB",s.substring(pos1,pos2+1)));
					}else if(s.substring(pos3+1,pos2).startsWith("NWWDDL")){
						model_list.add(new Model(workItemNo,"DDLJOB",s.substring(pos1,pos2+1)));
					}
				}
			}
		}
		
		//DEM load model
		//get work item summary
		attribute = workItemCommon.findAttribute(projectArea, attribute_group[10], monitor);
		String[] dem = null;
		if(attribute != null){
			dem = ((String)attribute.getValue(paramIAuditableCommon, wi, monitor)).replaceAll("&nbsp;", " ").split(" ");
			HashSet<String> demNo = new HashSet<String>();
			for(String s:dem){
				if(s.startsWith("CW")){
					demNo.add(s);
				}
			}
			if(demNo.size() > 0){
				String demList = demNo.toString();
				model_list.add(new Model(workItemNo,"DEM",demList.substring(1,demList.length()-1)));
			}
		}
		
		//return new workitem
		//for uat workitem list, no need to return model_list since no code promotion during uat promotion
		if("uat".equalsIgnoreCase(scope)){
			return new WorkItem(workItemType.toString(),workItemNo,workItemTitle,workItemStatus,null);
		}
		
		//for sit and prod, return model_list it gets from rtc
		return new WorkItem(workItemType.toString(),workItemNo,workItemTitle,workItemStatus,model_list);
	}
	
	private static class LoginHandler implements ILoginHandler, ILoginInfo {

		private String fUserId;
		private String fPassword;
		
		private LoginHandler(String userId, String password) {
			fUserId = userId;
			fPassword = password;
		}
		
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return fPassword;
		}

		@Override
		public String getUserId() {
			// TODO Auto-generated method stub
			return fUserId;
		}

		@Override
		public ILoginInfo challenge(ITeamRepository arg0) {
			// TODO Auto-generated method stub
			return this;
		}
	}
}
