// tag::head[]
package tacos.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@RepositoryRestController
public class DesignTacoController {
	
	private final TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	//public Resources<Resource<Taco>> recentTacos() {
	public ResponseEntity<Resources<TacoResource>> recentTacos(){
		PageRequest page = PageRequest.of(
		0, 12, Sort.by("createdAt").descending());
		
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		
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
				new TacoResourceAssembler().toResources(tacos);
		Resources<TacoResource> recentResources =
				new Resources<TacoResource>(tacoResources);
				recentResources.add(
				linkTo(methodOn(DesignTacoController.class).recentTacos())
				.withRel("recents"));
		return new ResponseEntity<>(recentResources, HttpStatus.OK);
	}
		
}