package tacos.messaging;

import tacos.JmsOrder;
import tacos.Order;

public interface OrderMessagingService {
	void sendOrder(JmsOrder order, String source);
}
