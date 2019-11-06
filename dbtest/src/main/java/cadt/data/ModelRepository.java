package cadt.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import cadt.domain.Model;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ModelRepository extends ReactiveCassandraRepository<Model, String>{
	public Flux<Model> findByWorkItemNo(String workItemNo);
	public Mono<Model> findByWorkItemNoAndTypeAndName(String workItemNo, String type, String name);
}