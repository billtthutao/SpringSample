package tacos;

import java.net.URI;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Test1 {

	private static RestTemplate rest = new RestTemplate();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ingredientId = "COTO";
		
		System.out.println("Testing of GET");
		Ingredient ingredient = getIngredientById4(ingredientId);
		System.out.println(ingredient.getId()+" "+ingredient.getName()+" "+ingredient.getType());
		
		System.out.println("Testing of PUT");
		UUID uid = UUID.fromString("xxxxxxxxx");
		Taco taco = getTacoById(uid);
		System.out.println(taco.getIngredients());
		taco.setId(UUID.randomUUID());
		//taco.setIngredients(Arrays.asList(ingredient));
		taco.setName("Test taco name");
		updateTaco(taco);
		taco = getTacoById(taco.getId());
		System.out.println(taco.getId()+" "+taco.getName()+" "+taco.getCreatedAt());
		
		//System.out.println("Testing of Delete");
		//taco.setId((long)2);
		//deleteTaco(taco);
		
		//System.out.println("Testing of Post");
		//Ingredient newIngredient = new Ingredient("TEST1", "Test Name1", Type.WRAP);
		//Ingredient returnIngredient = createIngredient(newIngredient);
		//System.out.println(returnIngredient);
		
		//Ingredient newIngredient2 = new Ingredient("TEST2", "Test Name2", Type.WRAP);
		//URI uri = createIngredient2(newIngredient2);
		//System.out.println(uri);
	}
	
	public static Ingredient getIngredientById(String ingredientId) {
		return rest.getForObject("http://localhost:8080/ingredients/{id}",Ingredient.class, ingredientId);
	}
	
	public static Ingredient getIngredientById2(String ingredientId) {
		
		Map<String,String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		
		return rest.getForObject("http://localhost:8080/ingredients/{id}",Ingredient.class, urlVariables);
	}

	public static Ingredient getIngredientById3(String ingredientId) {
		
		Map<String,String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/ingredients/{id}").build(urlVariables);
		
		return rest.getForObject(url, Ingredient.class);
	}
	
	public static Ingredient getIngredientById4(String ingredientId) {
		
		ResponseEntity<Ingredient> responseEntity =
		rest.getForEntity("http://localhost:8080/ingredients/{id}",Ingredient.class, ingredientId);
		
		System.out.println("Fetched time: " +responseEntity.getHeaders());
		
		return responseEntity.getBody();
	}
	
	public static void updateIngredient(Ingredient ingredient) {
		rest.put("http://localhost:8080/api/ingredients/{id}",ingredient,ingredient.getId());
	}
	
	public static Taco getTacoById(UUID tacoId) {
		return rest.getForObject("http://localhost:8080/api/tacos/{id}",Taco.class, tacoId);
	}
	
	public static void updateTaco(Taco taco) {
		rest.put("http://localhost:8080/api/tacos/{id}",taco,taco.getId());
	}
	
	public static void deleteIngredient(Ingredient ingredient) {
		rest.delete("http://localhost:8080/api/ingredients/{id}",
		ingredient.getId());
	}
	
	public static void deleteTaco(Taco taco) {
		rest.delete("http://localhost:8080/api/tacos/{id}",
		taco.getId());
	}
	
	public static Ingredient createIngredient(Ingredient ingredient) {
		return rest.postForObject("http://localhost:8080/api/ingredients",
		ingredient,
		Ingredient.class);
	}
	
	public static URI createIngredient2(Ingredient ingredient) {
		return rest.postForLocation("http://localhost:8080/api/ingredients",
		ingredient);
	}
	
	public Ingredient createIngredient3(Ingredient ingredient) {
		ResponseEntity<Ingredient> responseEntity =
		rest.postForEntity("http://localhost:8080/api/ingredients",
		ingredient,
		Ingredient.class);
		System.out.println("New resource created at " + responseEntity.getHeaders().getLocation());
		return responseEntity.getBody();
	}
}
