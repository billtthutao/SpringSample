package tacos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tacos.User;

@FeignClient("TestUsers-service")
@Profile("feign")
public interface FeignUserClient {

	  @GetMapping("/api/allUsers")
	  Iterable<User> getAllUsers();
	  
	  @GetMapping("/api/allUsers/{id}")
	  User getUserById(@PathVariable("id") int id);
}
