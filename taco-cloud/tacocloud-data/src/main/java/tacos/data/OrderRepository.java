package tacos.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.domain.Pageable;

import reactor.core.publisher.Flux;
import tacos.Order;
import tacos.User;

public interface OrderRepository extends ReactiveCassandraRepository<Order, UUID>{
	Flux<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
