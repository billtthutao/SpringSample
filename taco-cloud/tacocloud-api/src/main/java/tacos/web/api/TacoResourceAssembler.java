package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.*;
import tacos.data.IngredientRepository;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource>{

	private final IngredientRepository ingredientRepository;
	
	public TacoResourceAssembler(IngredientRepository ingredientRepository) {
		super(DesignTacoController2.class, TacoResource.class);
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	protected TacoResource instantiateResource(Taco taco) {
		return new TacoResource(taco,ingredientRepository);
	}
	
	@Override
	public TacoResource toResource(Taco taco) {
		// TODO Auto-generated method stub
		return createResourceWithId(taco.getId(), taco);
	}

}
