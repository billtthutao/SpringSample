package tacos.web.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/fluxapi/tacos",produces={"application/json"})
@CrossOrigin(origins="*")
public class FluxTacoController {
	
	@Autowired
	private final TacoRepository tacoRepository;
	
	public FluxTacoController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}
	
	@GetMapping(path="")
	public Flux<Taco> allTacos(){
		return Flux.fromIterable(tacoRepository.findAll());
	}
	
	@GetMapping(path="/{id}")
	public Mono<Taco> getTacoById(@PathVariable("id") UUID id){
		return Mono.justOrEmpty(tacoRepository.findById(id));
	}
	
	//@PostMapping(consumes="application/json")
	//@ResponseStatus(HttpStatus.CREATED)
	//public Mono<Taco> postTaco(@RequestBody Mono<Taco> tacoMono){
	//	System.out.println("postTaco");
	//	Taco taco = tacoMono.block();
	//	System.out.println(taco);
	//	return Mono.justOrEmpty(tacoRepository.save(taco));
	//}
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Taco> postTaco(@RequestBody Taco taco){
		System.out.println("postTaco");
		System.out.println(taco);
		return Mono.justOrEmpty(tacoRepository.save(taco));
	}
}
