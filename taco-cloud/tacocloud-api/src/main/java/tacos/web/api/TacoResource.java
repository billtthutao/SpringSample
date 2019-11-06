package tacos.web.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;
import tacos.Ingredient;
import tacos.IngredientUDT;
import tacos.Taco;
import tacos.data.IngredientRepository;

@Relation(value="taco", collectionRelation="tacos")
public class TacoResource extends ResourceSupport{
	
	@Getter
	private final Date createdAt;
	@Getter
	private final String name;
	@Getter
	private final List<IngredientResource> ingredientsResource;
	
	private final IngredientResourceAccembler ingredientResourceAccembler =
			new IngredientResourceAccembler();
	
	private final IngredientRepository ingredientRepository;
	
	public TacoResource(Taco taco, IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
		this.createdAt = taco.getCreatedAt();
		this.name = taco.getName();
		this.ingredientsResource = ingredientResourceAccembler.toResources(getIngredientByName(taco));
	}
	
	private List<Ingredient> getIngredientByName(Taco taco) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for(IngredientUDT ingredientUDT:taco.getIngredients()) {
			ingredientRepository.findByName(ingredientUDT.getName());
		}
		return ingredients;
	}
}
