package cadt;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySink {

    String input = "mySinkInput";   
    
    @Input(input)
    MessageChannel myInput();
}
