package cadt;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(MySink.class)
public class MyRecieveService {

    @StreamListener(MySink.str)
    public void recieve(Object payload){
        System.out.println("spring cloud stream1 receives "+payload+" by MyReceiveService");
    }
}
