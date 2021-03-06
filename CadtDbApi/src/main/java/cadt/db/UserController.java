package cadt.db;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cadt.data.UserRepository;
import cadt.domain.User;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/db",produces="application/json")
@CrossOrigin(origins="*")
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping(path="/user",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> postUser(@RequestBody User user){
		System.out.println("CreateUser");
		return userRepository.save(user);
	}
	
	@GetMapping(path="/user/validate")
	public Mono<User> validateUser(@RequestBody User user){
		System.out.println("ValidateUser");
		Mono<User> dbUser = userRepository.findByUsername(user.getUsername());
		
		if (user.getPassword().equals(dbUser.block().getPassword()))
			return dbUser;
		else
			return null;
	}
}
