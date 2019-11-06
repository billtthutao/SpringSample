package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Foo;

@RestController
@RequestMapping(path="/fluxapi",produces="application/json")
@CrossOrigin(origins="*")
public class FooController {
	
	@GetMapping(path="/foos")
	public Flux<Foo> allFoos(){
		Foo foo1 = new Foo(1,"Bill","Hu");
		Foo foo2 = new Foo(2,"Sofica","Yang");
		return Flux.just(foo1,foo2);
	}
	
	@GetMapping(path="/foos/{id}")
	public Mono<Foo> userById(@PathVariable("id") int id) {
		return Mono.just(new Foo(id,"Bill","Hu"));
	}
	
	@PostMapping(path="/foos",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Foo> postFoo(@RequestBody Foo foo){
		System.out.println("postFoo");
		return Mono.just(new Foo(3,"Bill", "Hu"));
	}
}
