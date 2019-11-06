package tacos;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tacos.Ingredient.Type;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
public class JmsIngredient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final Type type;
}
