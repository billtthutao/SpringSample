package cadt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Table("deployments")
public class Deployment {

	@PrimaryKey
	private UUID id = UUIDs.timeBased();
	private Date placedAt = new Date(); 
	private String deploymentName;
	private List<WorkItem> workitems = new ArrayList<WorkItem>();
	
	public void addWorkItem(WorkItem workitem) {
		this.workitems.add(workitem);
	}
	
	public String toString() {
		return id+":"+deploymentName;
	}
}
