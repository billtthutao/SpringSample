package tacos.webclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.User;

@Service
@Profile("webclient")
public class FluxUserServiceClient {
	private WebClient.Builder wcBuilder;
	  
	  public FluxUserServiceClient(@LoadBalanced WebClient.Builder wcBuilder) {
	    this.wcBuilder = wcBuilder;
	  }
	  
	  public Flux<User> getAllUsers() {
	    return wcBuilder.build()    
	        .get()      
	        .uri("http://Test6Webflux-service/api/users")    
	        .retrieve().bodyToFlux(User.class);
	  }
	  
	  public Mono<User> getUserById(int id){
		  return wcBuilder.build()    
			        .get()      
			        .uri("http://Test6Webflux-service/api/users/{id}", id)    
			        .retrieve().bodyToMono(User.class);
	  }
}
