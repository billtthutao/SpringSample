package tacos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig1 {
	@Bean
	public String test1() {
		System.out.println("test1()");
		return "test1";
	}
}
