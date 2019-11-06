package tacos.data;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.Taco;
public interface TacoRepository extends PagingAndSortingRepository<Taco, UUID>{
	
}
