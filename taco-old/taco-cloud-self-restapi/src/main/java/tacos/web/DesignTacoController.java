// tag::head[]
package tacos.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@RestController
@RequestMapping(path="/design",produces={"application/json", "text/xml"})
@CrossOrigin(origins="*")
public class DesignTacoController {
	
	private final TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo, UserRepository userRepo) {
		this.tacoRepo = designRepo;
	}
	
	@GetMapping("/recent")
	//public Resources<Resource<Taco>> recentTacos() {
	public Resources<TacoResource> recentTacos(){
	//public Resources<TaoResource> recentTacos() {
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
		return recentResources;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);
		}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}
}