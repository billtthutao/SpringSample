package tacos.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/fluxapi/ingredients",produces={"application/json"})
@CrossOrigin(origins="*")
public class FluxIngredientController {
	
	@Autowired
	private final IngredientRepository ingredientRepository;
	
	public FluxIngredientController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping(path="")
	public Flux<Ingredient> allIngredients(){
		return ingredientRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public Mono<Ingredient> getIngredientsById(@PathVariable("id") String id){
		return ingredientRepository.findById(id);
		
	}
}
