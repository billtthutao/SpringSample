package tacos.data;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.annotation.Secured;

import tacos.Taco;
@Secured("ROLE_USER")
public interface TacoRepository extends PagingAndSortingRepository<Taco, UUID>{

}
