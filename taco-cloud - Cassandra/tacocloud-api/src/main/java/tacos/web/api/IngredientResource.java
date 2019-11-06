package tacos.web.api;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import tacos.Ingredient;
import tacos.Ingredient.Type;

public class IngredientResource extends ResourceSupport{
	@Getter
	private final String name;
	@Getter
	private final Type type;
	
	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
}
