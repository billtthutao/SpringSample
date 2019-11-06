package cadt.domain;

public enum WorkItemType {
	STORY,
	PTR;
	
	public static WorkItemType getWorkItemType(String type){
		switch(type.toLowerCase()){
			case "story": return STORY;
			case "z-ceps-story": return STORY;
			case "ptr": return PTR;
			default: return null;
		}
	}
	
	public static String getWorkItemTypeValue(WorkItemType type) {
		switch(type){
			case STORY: return "story";
			case PTR: return "ptr";
			default: return null;
		}	
	}
}
