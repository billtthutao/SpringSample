package cadt.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import cadt.domain.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCassandraRepository<User, UUID>{
	
	@AllowFiltering
	Mono<User> findByUsername(String username);
	
	@AllowFiltering
	Mono<User> findByEmail(String email);
}
