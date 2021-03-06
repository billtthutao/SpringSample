package tacos.kitchen;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import tacos.JmsIngredient;
import tacos.JmsOrder;


//@Profile({"jms-template", "rabbitmq-template"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

  private final OrderReceiver orderReceiver;
  
  @GetMapping("/receive")
  public String receiveOrder(Model model) {
    JmsOrder order = orderReceiver.receiveOrder();
    System.out.println("receive order*********************");
    System.out.println(order.getTacos());
    if (order != null) {
      model.addAttribute("order", order);
      return "receiveOrder";
    }
    
    return "noOrder";
    
    
  }
  
  
}