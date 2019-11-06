package cadt.domain;

import java.util.HashSet;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("cadtworkitems")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class WorkItemDB {

	private String type;
	@PrimaryKey
	private String id;
	private String title;
	private String status;
	private HashSet<Model> modelList;
}
