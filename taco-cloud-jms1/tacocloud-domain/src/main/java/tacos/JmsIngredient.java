package tacos;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import tacos.Ingredient.Type;

@Data
@RequiredArgsConstructor
public class JmsIngredient implements Serializable{

  private final String name;
  private final Type type;
  
}
