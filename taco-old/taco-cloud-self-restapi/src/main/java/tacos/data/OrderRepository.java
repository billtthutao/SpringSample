package tacos.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;
import tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	Optional<Order> findById(Long id);
}
