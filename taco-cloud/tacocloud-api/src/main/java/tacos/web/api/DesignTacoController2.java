package tacos.web.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@RepositoryRestController
public class DesignTacoController2 {
	
	private final TacoRepository tacoRepo;
	private final IngredientRepository ingredientRepository;
	
	@Autowired
	public DesignTacoController2(TacoRepository tacoRepo, IngredientRepository ingredientRepository) {
		this.tacoRepo = tacoRepo;
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	//public Resources<Resource<Taco>> recentTacos() {
	public ResponseEntity<Resources<TacoResource>> recentTacos(){
		PageRequest page = PageRequest.of(
		0, 12, Sort.by("createdAt").descending());
		
		Iterable<Taco> tacos = tacoRepo.findAll().take(12).toIterable();
		
		//Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
		//recentResources.add(new Link("http://localhost:8080/design/recent", "recents"));
		
		//Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
		//recentResources.add(ControllerLinkBuilder.linkTo(DesignTacoController.class).slash("recent").withRel("recents"));
		
		//Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
		//for(Resource<Taco> r:recentResources) {
		//	r.add(linkTo(methodOn(DesignTacoController.class).tacoById(r.getContent().getId()))
		//			.withRel("self"));
		//}
		//recentResources.add(
		//		linkTo(methodOn(DesignTacoController.class).recentTacos())
		//		.withRel("recents"));

		List<TacoResource> tacoResources =
				new TacoResourceAssembler(ingredientRepository).toResources(tacos);
		Resources<TacoResource> recentResources =
				new Resources<TacoResource>(tacoResources);
				recentResources.add(
				linkTo(methodOn(DesignTacoController2.class).recentTacos())
				.withRel("recents"));
		return new ResponseEntity<>(recentResources, HttpStatus.OK);
	}
		
}
