package tacos.kitchen;

import tacos.JmsOrder;

public interface OrderReceiver {

  JmsOrder receiveOrder();

}