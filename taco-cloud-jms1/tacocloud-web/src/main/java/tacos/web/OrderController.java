package tacos.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.JmsIngredient;
import tacos.JmsOrder;
import tacos.JmsTaco;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private final OrderRepository orderRepositroy;
	private OrderProps props;
	private OrderMessagingService orderMessagingService;
	
	@Autowired
	public OrderController(OrderRepository orderRepositroy, OrderProps props, OrderMessagingService orderMessagingService) {
		this.orderRepositroy = orderRepositroy;
		this.props = props;
		this.orderMessagingService = orderMessagingService;
	}
	
	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		List<Order> orders = orderRepositroy.findByUserOrderByPlacedAtDesc(user,pageable);
		model.addAttribute("orders", orders);
		
		return "orderList";
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
							   @AuthenticationPrincipal User user) {
	  if (errors.hasErrors()) {
		  return "orderForm";
	  }
	    
	  order.setUser(user);
	  orderRepositroy.save(order);
	  
	  System.out.println("Send order");
	  System.out.println(order.getTacos());
	  orderMessagingService.sendOrder(buildOrder(order),"WEB");
	  
	  sessionStatus.setComplete();
	  
	  log.info("Order submitted: " + order);
	  
	  return "redirect:/";
	}
	
	private JmsOrder buildOrder(Order order) {
		JmsOrder jmsOrder = new JmsOrder(order.getPlacedAt(), order.getDeliveryName(), order.getDeliveryStreet(), 
				order.getDeliveryCity(), order.getDeliveryState(), order.getDeliveryZip());
		
		List<JmsTaco> jmsTacos = jmsOrder.getTacos();
		for(Taco taco:order.getTacos()) {
			JmsTaco jmsTaco = new JmsTaco(taco.getName(), taco.getCreatedAt());
			List<JmsIngredient> jmsIngredients = jmsTaco.getIngredients();
			for(Ingredient ingredient:taco.getIngredients()) {
				jmsIngredients.add(new JmsIngredient(ingredient.getName(), ingredient.getType()));
			}
			jmsTacos.add(jmsTaco);
		}
		return jmsOrder;
	}
}
