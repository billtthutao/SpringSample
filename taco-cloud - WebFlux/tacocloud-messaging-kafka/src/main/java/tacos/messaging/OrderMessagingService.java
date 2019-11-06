package tacos.messaging;

import tacos.JmsOrder;

public interface OrderMessagingService {
	void sendOrder(JmsOrder order, String source);
}
