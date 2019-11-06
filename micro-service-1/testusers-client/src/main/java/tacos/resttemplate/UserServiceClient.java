package tacos.resttemplate;

import java.util.Arrays;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tacos.User;

@Service
@Profile("resttemplate")
public class UserServiceClient {
	private RestTemplate rest;
	
	public UserServiceClient(RestTemplate rest) {
		this.rest = rest;
	}
	
	public Iterable<User> getAllUsers(){
		User[] users = rest.getForObject("http://TestUsers-service/api/allUsers", User[].class);
		return Arrays.asList(users);
	}
	
	public User getUserById(int id) {
		return rest.getForObject("http://TestUsers-service/api/allUsers/{id}", User.class,id);
	}
}
