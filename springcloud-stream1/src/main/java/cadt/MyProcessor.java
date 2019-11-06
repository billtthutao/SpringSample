package cadt;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MyProcessor {

    String input = "myProcessorInput";   
    String output = "myProcessorOutput";
    
    @Input(input)
    MessageChannel myProcessorInput();
    
    @Output(output)
    MessageChannel myProcessorOutput();
}
