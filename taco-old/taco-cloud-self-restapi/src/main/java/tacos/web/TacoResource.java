package tacos.web;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;
import tacos.Taco;

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
	
	public TacoResource(Taco taco) {
		this.createdAt = taco.getCreatedAt();
		this.name = taco.getName();
		this.ingredientsResource = ingredientResourceAccembler.toResources(taco.getIngredients());
		
	}
}
