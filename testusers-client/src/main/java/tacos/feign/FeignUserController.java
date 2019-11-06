package tacos.feign;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/feignUsers")
@Slf4j
@Profile("feign")
public class FeignUserController {
	
	private FeignUserClient feignUserClient;
	
	public FeignUserController(FeignUserClient feignUserClient) {
		this.feignUserClient = feignUserClient;
	}
	
	 @GetMapping
	 public String userList(Model model) {
	    log.info("Fetched all users from a Feign client.");
	    model.addAttribute("users", feignUserClient.getAllUsers());
	    return "userList";
	  }
	 
	 @GetMapping("/{id}")
	 public String getUserById(@PathVariable("id") int id, Model model) {
	    log.info("Fetched user from a Feign client.");
	    model.addAttribute("users", Arrays.asList(feignUserClient.getUserById(id)));
	    return "userList";
	  }
}
