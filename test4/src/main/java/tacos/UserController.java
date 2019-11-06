package tacos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api1/users",produces="application/json")
@CrossOrigin(origins="*")
public class UserController {
	
	@GetMapping(produces="application/json")
	public Iterable<User> allUsers() {
		User user1 = new User("","","","","","","","","");
		User user2 = new User("","","","","","","","","");
		List<User> result = new ArrayList<User>();
		result.add(user1);
		result.add(user2);
	    return result; 
	  }
}
