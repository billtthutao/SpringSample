package cadt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class PersonConfig {

	@Bean
	public Person student() {
		return new Student();
	}
	
	@Bean(name="worker1")
	public Person worker() {
		return new Worker();
	}
}
