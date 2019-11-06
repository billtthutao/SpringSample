package tacos.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/ingredients",produces={"application/json", "text/xml"})
@CrossOrigin(origins="*")
public class IngredientController {
	private final IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") String id){
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		if(ingredient.isPresent())
			return new ResponseEntity<Ingredient>(ingredient.get(),HttpStatus.OK);
		else 
			return new ResponseEntity<Ingredient>(ingredient.get(),HttpStatus.NOT_FOUND);
	}
}
