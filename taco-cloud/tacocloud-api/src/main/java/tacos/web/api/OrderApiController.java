package tacos.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import tacos.IngredientUDT;
import tacos.JmsIngredient;
import tacos.JmsOrder;
import tacos.JmsTaco;
import tacos.Order;
import tacos.TacoUDT;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService;

@RestController
@RequestMapping(path="/api2/orders",produces="application/json")
@CrossOrigin(origins="*")
public class OrderApiController {
	private OrderRepository repo;
	private OrderMessagingService orderMessages;
	private EmailOrderService emailOrderService;
	
	public OrderApiController(OrderRepository repo,OrderMessagingService orderMessages,
			EmailOrderService emailOrderService) {
		this.repo = repo;
		this.orderMessages = orderMessages;
		this.emailOrderService = emailOrderService;
	}
	
	@GetMapping(produces="application/json")
	public Iterable<Order> allOrders() {
	    return repo.findAll().toIterable();
	  }

	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order postOrder(@RequestBody Order order) {
	    orderMessages.sendOrder(buildOrder(order),"API");
	    return repo.save(order).block();
	}
	
	@PostMapping(path="fromEmail", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order postOrderFromEmail(@RequestBody EmailOrder emailOrder) {
	    Order order = emailOrderService.convertEmailOrderToDomainOrder(emailOrder);
	    orderMessages.sendOrder(buildOrder(order),"EMAIL");
	    return repo.save(order).block();
	}
	
	@PutMapping(path="/{orderId}", consumes="application/json")
	  public Order putOrder(@RequestBody Order order) {
	    return repo.save(order).block();
	  }

	  @PatchMapping(path="/{orderId}", consumes="application/json")
	  public Order patchOrder(@PathVariable("orderId") UUID orderId,
	                          @RequestBody Order patch) {

	    Order order = repo.findById(orderId).block();
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
	    return repo.save(order).block();
	  }

	  @DeleteMapping("/{orderId}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteOrder(@PathVariable("orderId") UUID orderId) {
	    try {
	      repo.deleteById(orderId);
	    } catch (EmptyResultDataAccessException e) {}
	  }
	  
	//Convert Order to JmsOrder
	private JmsOrder buildOrder(Order order) {
		JmsOrder jmsOrder = new JmsOrder(order.getPlacedAt(), order.getDeliveryName(), order.getDeliveryStreet(), 
				order.getDeliveryCity(), order.getDeliveryState(), order.getDeliveryZip(), new ArrayList<JmsTaco>());
		
		List<JmsTaco> jmsTacos = jmsOrder.getTacos();
		for(TacoUDT taco:order.getTacos()) {
			JmsTaco jmsTaco = new JmsTaco(taco.getName(), new ArrayList<JmsIngredient>());
			List<JmsIngredient> jmsIngredients = jmsTaco.getIngredients();
			for(IngredientUDT ingredient:taco.getIngredients()) {
				jmsIngredients.add(new JmsIngredient(ingredient.getName(), ingredient.getType()));
			}
			jmsTacos.add(jmsTaco);
		}
		return jmsOrder;
	}
}
