package tacos;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		test2();
		TimeUnit.SECONDS.sleep(60);
	}

	public static void test1(){
		Flux<User> users = 
				WebClient.create()
				.get()
				.uri("http://localhost:8080/api/allUsers")
				.retrieve()
				.bodyToFlux(User.class);
	
		users.subscribe(e -> System.out.println(e));
	}
	
	public static void test2(){
		
		Mono<User> user = WebClient.create()
				.get()
				.uri("http://localhost:8080/api/users/{id}", 1)
				.retrieve()
				.bodyToMono(User.class);
		
		User u = user.block();
		
		System.out.println(u);
	}
}

