package cadt;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(MySink.class)
public class MyRecieveService {

    @StreamListener(MySink.input)
    @SendTo(MySink.ack)
    public Object recieve(Object payload){
        System.out.println("spring cloud stream2 receives "+payload+" by MyReceiveService");
        return "Acknowledge: Message "+payload+" has been received by spring cloud stream2";
    }
}
