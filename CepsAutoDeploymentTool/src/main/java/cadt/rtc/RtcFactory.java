package cadt.rtc;

import java.util.HashSet;

import cadt.domain.WorkItem;

public interface RtcFactory {
	public HashSet<WorkItem> fetchWorkItemByStatus(String status);
	
	public WorkItem fetchWorkItemById(String id);
	
	public int updateWorkItemStatus(String id, String newState);
}
