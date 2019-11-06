package cadt.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import cadt.domain.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCassandraRepository<User, String>{

	public Mono<User> findByUsername(String username);
}
