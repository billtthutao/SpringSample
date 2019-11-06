package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.IngredientUDT;
import tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  
  @Override
  public IngredientUDT convert(String id) {
	Ingredient ingredient = ingredientRepo.findById(id).block();
    return new IngredientUDT(ingredient.getName(),ingredient.getType());
  }

}
