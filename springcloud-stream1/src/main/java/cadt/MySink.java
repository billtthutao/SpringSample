package cadt;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySink {

    String str = "myInput";   
    
    @Input(str)
    MessageChannel myInput();
}
