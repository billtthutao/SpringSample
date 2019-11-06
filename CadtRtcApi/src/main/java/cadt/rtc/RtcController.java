package cadt.rtc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cadt.domain.WorkItem;


@RestController
@RequestMapping(path="/rtc",produces="application/json")
@CrossOrigin(origins="*")
public class RtcController {
	
	private final RtcFactory factory;
	
	@Autowired
	public RtcController(RtcFactory factory) {
		this.factory = factory;
	}
	
	@GetMapping(path="/workitems/status/{status}",produces="application/json")
	public Iterable<WorkItem> fetchWorkitemByStatus(@PathVariable("status") String status) {
		System.out.println("fetchWorkitemByStatus:"+status);
		return factory.fetchWorkItemByStatus(status);
	}
	
	@GetMapping(path="/workitem/{id}",produces="application/json")
	public WorkItem fetchWorkitemById(@PathVariable("id") String id) {
		System.out.println("fetchWorkitemById:"+id);
		return factory.fetchWorkItemById(id);
	}
	
	@PostMapping(path="/workitem/setStatus/{id}",consumes="application/json")
	public WorkItem setWorkItemStatus(@RequestBody WorkItem workitem) {
		
		 int result = factory.updateWorkItemStatus(workitem.getId(), workitem.getStatus());
		 
		 if (result < 0)
			 return null;
		 else
			 return workitem;
	}
}
