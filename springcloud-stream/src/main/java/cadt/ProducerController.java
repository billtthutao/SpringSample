package cadt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
 
    @Autowired
    private SendService sendService;
    @Autowired
    private MySendService mySendService;
    
    @RequestMapping("/send/{msg}")
    public void send(@PathVariable("msg") String msg){
        sendService.sendMsg(msg);
    }
    
    @RequestMapping("/mysend/{msg}")
    public void mysend(@PathVariable("msg") String msg){
    	mySendService.sendMsg(msg);
    }
    
    @RequestMapping("/mysendprocessor/{msg}")
    public void mysendprocessor(@PathVariable("msg") String msg){
    	mySendService.sendMsgProcessor(msg);
    }
}
