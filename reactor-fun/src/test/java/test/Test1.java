package test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		test2();
		TimeUnit.SECONDS.sleep(10);
	}
	
	public static void test1() {
		Flux<String> fruitFlux = Flux
			      .just("Apple", "Orange", "Grape", "Banana", "Strawberry");
		
		fruitFlux.subscribe(
				f -> System.out.println("Here's some fruit: " + f)
		);
		
	}
	
	public static void test2() {
		Flux<Long> intervalFlux =
				Flux.interval(Duration.ofSeconds(1))
				.take(5);
		
		intervalFlux.subscribe(
				f -> System.out.println(""+f)
		);
		
	}
	
	
}
