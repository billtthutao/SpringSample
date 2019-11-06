package tacos.resttemplate;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.User;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/allUsers")
@Profile("resttemplate")
public class UserController {
	
	private final UserServiceClient userServiceClient;
	
	public UserController(UserServiceClient userServiceClient) {
		System.out.println("resttemplate UserController");
		this.userServiceClient = userServiceClient;
	}
	
	 @GetMapping
	 public String userList(Model model) {
		 log.info("Fetched all users from a RestTemplate-based service.");
		 model.addAttribute("users", userServiceClient.getAllUsers());
		 return "userList";
	 }
	 
	 @GetMapping("/{id}")
	 public String getUserById(@PathVariable("id") int id,Model model) {
		 log.info("Fetched user from a RestTemplate-based service.");
		 model.addAttribute("users", Arrays.asList(userServiceClient.getUserById(id)));
		 return "userList";
	 }
}
