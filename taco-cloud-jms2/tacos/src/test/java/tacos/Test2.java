package tacos;

import java.net.URI;
import java.util.Collection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.Resources;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Traverson traverson = new Traverson(
				URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
		
		ParameterizedTypeReference<Resources<Ingredient>> ingredientType =
				new ParameterizedTypeReference<Resources<Ingredient>>() {};
				Resources<Ingredient> ingredientRes =
				traverson
				.follow("ingredients")
				.toObject(ingredientType);
		
		Collection<Ingredient> ingredients = ingredientRes.getContent();
				
		for(Ingredient ingredient:ingredients) {
					System.out.println(ingredient);
		}
		
		String ingredientsUrl = traverson
				.follow("ingredients")
				.asLink()
				.getHref();
		
		System.out.println(ingredientsUrl);
	}	

}
