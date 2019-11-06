package tacos;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		test1();
		
		TimeUnit.SECONDS.sleep(20);
	}

	public static void test1() {
		Flux<Taco> tacos = 
				WebClient.create()
				.get()
				.uri("http://localhost:8080/api1/tacos/recent")
				.retrieve()
				.bodyToFlux(Taco.class);
		
		tacos.subscribe(e -> System.out.println(e));
	}
}
