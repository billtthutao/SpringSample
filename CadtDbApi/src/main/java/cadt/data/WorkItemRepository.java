package cadt.data;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import cadt.domain.WorkItem;
import cadt.domain.WorkItemDB;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkItemRepository extends ReactiveCassandraRepository<WorkItemDB, String>{

	public Mono<WorkItemDB> findById(String id);
	
	@AllowFiltering
	public Flux<WorkItemDB> findByStatus(String status);
}
