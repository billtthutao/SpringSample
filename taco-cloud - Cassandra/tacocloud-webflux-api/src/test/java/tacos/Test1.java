package tacos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient.Type;

public class Test1 {
	public static void main(String[] args) throws InterruptedException {
		test5();
		TimeUnit.SECONDS.sleep(60);
	}
	
	public static void test1(){
		Flux<Foo> users = 
				WebClient.create()
				.get()
				.uri("http://localhost:8080/fluxapi/foos")
				.retrieve()
				.bodyToFlux(Foo.class);
	
		users.subscribe(e -> System.out.println(e));
	}
	
	public static void test2(){
		
		Mono<Foo> foo = WebClient.create()
				.get()
				.uri("http://localhost:8080/fluxapi/foos/{id}", 1)
				.retrieve()
				.bodyToMono(Foo.class);
		
		Foo u = foo.block();
		
		System.out.println(u);
	}
	
	public static void test3() {
		Flux<Ingredient> ingredients =
				WebClient.create()
				.get()
				.uri("http://localhost:8080/fluxapi/ingredients")
				.retrieve()
				.bodyToFlux(Ingredient.class);
		
		ingredients.subscribe(e -> System.out.println(e));
	}
	
	public static void test4(){
		
		Mono<Ingredient> ingredient = WebClient.create()
				.get()
				.uri("http://localhost:8080/fluxapi/ingredients/{id}", "FLTO")
				.retrieve()
				.bodyToMono(Ingredient.class);
		
		Ingredient u = ingredient.block();
		
		System.out.println(u);
	}
	
	public static void test5() throws InterruptedException{
		Mono<Taco> tacoMono = Mono.just(testTaco((long)1));
		
		Mono<Taco> result = WebClient.create()
				.post()
				.uri("http://localhost:8080/fluxapi/tacos")
				.accept(MediaType.APPLICATION_JSON)
				.body(tacoMono, Taco.class)
				//.syncBody(testTaco((long)1))
				.retrieve()
				.bodyToMono(Taco.class);
				//.exchange()
				//.flatMap(cr -> cr.bodyToMono(Taco.class));
		
		result.subscribe(e -> System.out.println(e));
	}
	  
	private static Taco testTaco(Long number) {
		Taco taco = new Taco();
		taco.setId(UUID.randomUUID());
		taco.setName("Taco " + number);
		
		List<IngredientUDT> ingredients = new ArrayList<>();
		
		ingredients.add(new IngredientUDT("Ingredient A", Type.WRAP));
		ingredients.add(new IngredientUDT("Ingredient B", Type.PROTEIN));
		    
		taco.setIngredients(ingredients);
		    
		return taco;
	}
	
	public static void test6() {
		Mono<Foo> fooMono = Mono.just(new Foo(3,"Bill", "Hu"));
		
		Mono<Foo> result = WebClient.create()
				.post()
				.uri("http://localhost:8080/fluxapi/foos")
				.accept(MediaType.APPLICATION_JSON)
				.body(fooMono, Foo.class)
				.retrieve()
				.bodyToMono(Foo.class);
			
		result.subscribe(e -> System.out.println(e));
	}
}
