package tacos.data;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.Ingredient;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String>{
	@AllowFiltering
	public Mono<Ingredient> findByName(String name);
}
