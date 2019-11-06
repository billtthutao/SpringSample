package tacos.web;

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
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private final OrderRepository orderRepositroy;
	private OrderProps props;
	
	@Autowired
	public OrderController(OrderRepository orderRepositroy, OrderProps props) {
		this.orderRepositroy = orderRepositroy;
		this.props = props;
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
	  sessionStatus.setComplete();
	  
	  log.info("Order submitted: " + order);
	  
	  return "redirect:/";
	}
}
