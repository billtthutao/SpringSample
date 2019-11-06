package cadt.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import cadt.domain.WorkItem;
import reactor.core.publisher.Mono;

public interface WorkItemRepository extends ReactiveCassandraRepository<WorkItem, String>{

	public Mono<WorkItem> findById(String id);
}
