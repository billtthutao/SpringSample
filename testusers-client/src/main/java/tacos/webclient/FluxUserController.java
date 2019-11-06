package tacos.webclient;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/fluxUsers")
@Profile("webclient")
public class FluxUserController {

	 private final FluxUserServiceClient userServiceClient;
	 
	 public FluxUserController(FluxUserServiceClient userServiceClient) {
		 this.userServiceClient = userServiceClient;
	 }
	 
	 @GetMapping
	 public String userList(Model model) {
		 log.info("Fetched all users from a WebClient-based service.");
		 model.addAttribute("users", userServiceClient.getAllUsers().toIterable());
		 return "userList";
	 }
	 
	 @GetMapping("/{id}")
	 public String getUserById(@PathVariable("id") int id,Model model) {
		 log.info("Fetched user from a WebClient-based service.");
		 model.addAttribute("users",Arrays.asList(userServiceClient.getUserById(id).block()));
		 return "userList";
	 }
}
