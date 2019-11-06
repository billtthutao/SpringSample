package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/api1/tacos",produces="application/json")
@CrossOrigin(origins="*")
public class TacoController {
	
	private final TacoRepository tacoRepo;
	
	@Autowired
	public TacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(produces="application/json")
	public Iterable<Taco> allTacos(){
		System.out.println("allTacos");
		return tacoRepo.findAll(); 
	}
	
	@GetMapping("/recent")
	public Flux<Taco> recentTacos(){
		System.out.println("recentTacos");
		return Flux.fromIterable(tacoRepo.findAll()).take(12);
	}
}
