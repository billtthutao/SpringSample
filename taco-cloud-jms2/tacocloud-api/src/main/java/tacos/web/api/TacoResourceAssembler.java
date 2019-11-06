package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.*;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource>{

	public TacoResourceAssembler() {
		super(DesignTacoController2.class, TacoResource.class);
	}

	@Override
	protected TacoResource instantiateResource(Taco taco) {
		return new TacoResource(taco);
	}
	
	@Override
	public TacoResource toResource(Taco taco) {
		// TODO Auto-generated method stub
		return createResourceWithId(taco.getId(), taco);
	}

}
