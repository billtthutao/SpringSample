package cadt.domain;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Table("cadtmodels")
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class ModelDB {
	
	@PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
	private final String workItemNo;
	@PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
	private final String type;
	@PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
	private final String name;
	
}
