package tacos.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import tacos.JmsOrder;

@Service
public class JmsOrderMessagingService implements OrderMessagingService{

	private JmsTemplate jms;
	private Destination orderQueue;
	
	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {
		this.jms = jms;
		this.orderQueue = orderQueue;
	}
	
	@Override
	public void sendOrder(JmsOrder order, String source) {
		jms.convertAndSend(orderQueue, order, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setStringProperty("X_ORDER_SOURCE", source);
					return message;
			}
		});
		
		// TODO Auto-generated method stub
		//jms.send(orderQueue,
		//		new MessageCreator() {
		//			@Override 
		//			public Message createMessage(Session session) throws JMSException {
		//				Message message = session.createObjectMessage(order);
		//				message.setStringProperty("X_ORDER_SOURCE", source);
		//				return message;
		//			}
		//		}
		//);
		
		//jms.send("tacocloud.order.queue",
		//		session -> {
		//			Message message = session.createObjectMessage(order);
		//			message.setStringProperty("X_ORDER_SOURCE", source);
		//		});
		
		//jms.convertAndSend("tacocloud.order.queue", order, new MessagePostProcessor() {
		//	@Override
		//	public Message postProcessMessage(Message message) throws JMSException {
		//		message.setStringProperty("X_ORDER_SOURCE", source);
		//		return message;
		//	}
		//});
		
		//jms.convertAndSend("tacocloud.order.queue", order,
		//		message -> {
		//		message.setStringProperty("X_ORDER_SOURCE", source);
		//		return message;
		//});
	}
	
	//Use Lamba to avoid using new MessageCreator(){....}
	
	//public void sendOrder(Order order) {
	//	jms.send(
	//	orderQueue,
	//	session -> session.createObjectMessage(order));
	//}
	
	//use String destination directly
	
	//public void sendOrder(Order order) {
	//	jms.send(
	//	"tacocloud.order.queue",
	//	session -> session.createObjectMessage(order));
	//}

	//CONVERTING MESSAGES BEFORE SENDING
	//It uses jms.convertAndSend to avoid using the explicit MessageCreator(){....}
	
	//public void sendOrder(Order order) {
	//	jms.convertAndSend("tacocloud.order.queue", order);
	//}
}
