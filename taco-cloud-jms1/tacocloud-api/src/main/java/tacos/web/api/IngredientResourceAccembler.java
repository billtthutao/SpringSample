package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.Ingredient;

public class IngredientResourceAccembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

	public IngredientResourceAccembler() {
		super(IngredientController.class, IngredientResource.class);
	}
	
	@Override
	protected IngredientResource instantiateResource(Ingredient ingredient) {
		return new IngredientResource(ingredient);
	}
	
	@Override
	public IngredientResource toResource(Ingredient ingredient) {
		return createResourceWithId(ingredient.getId(), ingredient);
	}

}
