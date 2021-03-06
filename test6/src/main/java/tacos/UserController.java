package tacos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/api",produces="application/json")
@CrossOrigin(origins="*")
public class UserController {

	@GetMapping(path="allUsers",produces="application/json")
	public Iterable<User> allUsers(){
		System.out.println("allUsers");
		User user1 = new User(1,"Bill","Hu");
		User user2 = new User(2,"Sofica","Yang");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		return users; 
	}
	
	public Mono<ServerResponse> users(ServerRequest request){
		User user1 = new User(1,"Bill","Hu");
		User user2 = new User(2,"Sofica","Yang");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		Flux<User> userFlux = Flux.fromIterable(users);
		return ServerResponse.ok()
				.body(userFlux, User.class);
	}
	
	@GetMapping("/users/{id}")
	public Mono<User> userById(@PathVariable("id") int id) {
		System.out.println("userById");
		return Mono.just(new User(id,"Bill","Hu"));
	}
}
