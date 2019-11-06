package tacos.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.TacoUDT;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private final TacoRepository designRepo;
	private final UserRepository userRepo;

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo, UserRepository userRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
		this.userRepo = userRepo;
	}
	
	  @ModelAttribute
	  public void fetchIngredients(Model model) throws InterruptedException {
		//must invoke block() to make sure the db save operation to complete. I do not know why.  
		List<Ingredient> ingredients = ingredientRepo.findAll().buffer().blockFirst();		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					           filterByType(ingredients, type));
		}
	  }
	  
	 @GetMapping
	 public String showDesignForm(Model model, Principal principal) {	
		  
		  String username = principal.getName();
		  User user = userRepo.findByUsername(username).block();
		  model.addAttribute("user", user);
		  
	      return "design";
	  }
	 
	 @PostMapping
	  public String processDesign(@Valid Taco design, 
			  Errors errors, @ModelAttribute Order order) {
		
	    if (errors.hasErrors()) {
	      return "design";
	    }
	    
	  //must invoke block() to make sure the db save operation to complete. I do not know why.
	    Taco saved = designRepo.save(design).block();
	    order.addDesign(new TacoUDT(saved.getName(),saved.getIngredients()));
	    log.info("Processing design: " + design);

	    return "redirect:/orders/current";
	  }
	 
	  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		    return ingredients
		              .stream()
		              .filter(x -> x.getType().equals(type))
		              .collect(Collectors.toList());
	  }
}
