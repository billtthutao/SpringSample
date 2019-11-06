// tag::head[]
package tacos.web;

import java.util.ArrayList;
import java.util.List;
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
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	private final TacoRepository designRepo;

	@ModelAttribute(name = "order")
	public Order order() {
		System.out.println("ModelAttribute Order()");
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		System.out.println("ModelAttribute Taco()");
		return new Taco();
	}
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}
	
	@ModelAttribute
	public void fetchIngredientList(Model model) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();	
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
			
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					           filterByType(ingredients, type));
		}
	}
	
    @GetMapping
    public String showDesignForm(Model model) {
      return "design";
    }

  @PostMapping
  public String processDesign(@Valid Taco design, 
		  Errors errors, @ModelAttribute Order order) {
	
    if (errors.hasErrors()) {
      return "design";
    }
    
    Taco saved = designRepo.save(design);
    order.addDesign(saved);
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