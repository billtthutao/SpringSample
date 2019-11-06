package cadt.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api",produces="application/json")
@CrossOrigin(origins="*")
public class User1Controller {

	@GetMapping(path="allUsers",produces="application/json")
	public Iterable<User1> allUsers(){
		System.out.println("allUsers");
		User1 user1 = new User1(1,"Bill","Hu");
		User1 user2 = new User1(2,"Sofica","Yang");
		List<User1> users = new ArrayList<User1>();
		users.add(user1);
		users.add(user2);
		return users; 
	}
	
	@GetMapping("/users/{id}")
	public User1 userById(@PathVariable("id") int id) {
		System.out.println("userById");
		return new User1(id,"Bill","Hu");
	}
}
