package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.Order;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path="/orders",produces={"application/json", "text/xml"})
@CrossOrigin(origins="*")
public class OrderController {

	private final OrderRepository orderRepositroy;
	
	@Autowired
	public OrderController(OrderRepository orderRepositroy, OrderProps props) {
		this.orderRepositroy = orderRepositroy;
	}
	
	@GetMapping(produces="application/json")
	public Iterable<Order> allOrders () {
		return orderRepositroy.findAll();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order postOrder(@RequestBody Order order) {
	  return orderRepositroy.save(order);
	}
	
	@PutMapping(consumes="application/json")
	public Order putOrder(@RequestBody Order order) {
		return orderRepositroy.save(order);
	}
	
	@PatchMapping(path="/{orderId}", consumes="application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
		 
		Order order = orderRepositroy.findById(orderId).get();
		 
		 if (patch.getDeliveryName() != null) {
		      order.setDeliveryName(patch.getDeliveryName());
		    }
		    if (patch.getDeliveryStreet() != null) {
		      order.setDeliveryStreet(patch.getDeliveryStreet());
		    }
		    if (patch.getDeliveryCity() != null) {
		      order.setDeliveryCity(patch.getDeliveryCity());
		    }
		    if (patch.getDeliveryState() != null) {
		      order.setDeliveryState(patch.getDeliveryState());
		    }
		    if (patch.getDeliveryZip() != null) {
		      order.setDeliveryZip(patch.getDeliveryState());
		    }
		    if (patch.getCcNumber() != null) {
		      order.setCcNumber(patch.getCcNumber());
		    }
		    if (patch.getCcExpiration() != null) {
		      order.setCcExpiration(patch.getCcExpiration());
		    }
		    if (patch.getCcCVV() != null) {
		      order.setCcCVV(patch.getCcCVV());
		    }
		    return orderRepositroy.save(order);
	}
	 
	@DeleteMapping(path="/{orderId}", consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			orderRepositroy.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {}
	}
}
