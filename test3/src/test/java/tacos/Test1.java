package tacos;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class Test1 {
	public static void main(String[] args) {
		RestTemplate rest = new RestTemplate();
		
		Taco[] tacos = rest.getForObject("http://localhost:8080/api1/tacos",Taco[].class);
		
		List<Taco> list = Arrays.asList(tacos);
		
		for(Taco taco:list) {
			System.out.println(taco);
		}
	}
}
