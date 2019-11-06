package tacos.resttemplate;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import tacos.User;

@Service
@Profile("resttemplate")
public class UserServiceClient {
	private RestTemplate rest;
	
	public UserServiceClient(RestTemplate rest) {
		this.rest = rest;
	}
	
	@HystrixCommand(fallbackMethod="getDefaultUsers")
	public Iterable<User> getAllUsers(){
		User[] users = rest.getForObject("http://TestUsers-service/api/allUsers", User[].class);
		return Arrays.asList(users);
	}
	
	public Iterable<User> getDefaultUsers(){
		User user1 = new User(0,"Default","User");
		User user2 = new User(0,"Default","User");
		return Arrays.asList(user1,user2);
	}
	
	public User getUserById(int id) {
		return rest.getForObject("http://TestUsers-service/api/allUsers/{id}", User.class,id);
	}
}
