package cadt;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(MyProcessor.class)
public class ProcessorService {

    @ServiceActivator(inputChannel = MyProcessor.input,outputChannel = MyProcessor.output)
    public Object transform(Object payload){
        System.out.println("Message Processor："+payload);
        return payload;
    }
}
