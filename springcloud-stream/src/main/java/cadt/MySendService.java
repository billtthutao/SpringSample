package cadt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(MySource.class)
public class MySendService {
    
    @Autowired
    private MySource source;
 
    public void sendMsg(String msg){
    	System.out.println("spring cloud stream sends "+msg+" By MySendService");
        source.myOutput().send(MessageBuilder.withPayload(msg).build());
    }
    
    public void sendMsgProcessor(String msg){
    	System.out.println("spring cloud stream sends "+msg+" By MySendService with processor");
        source.myProcessor().send(MessageBuilder.withPayload(msg).build());
    }
 
}
