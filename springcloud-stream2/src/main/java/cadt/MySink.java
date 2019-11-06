package cadt;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String input = "myInput";   
    String ack = "MyAck";
    
    @Input(input)
    MessageChannel myInput();
    
    @Output(ack)
    SubscribableChannel myAck();
}
