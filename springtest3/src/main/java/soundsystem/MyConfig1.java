package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MyConfig1 {
	
	@Bean
	public MyClass3 myClass3() {
		return new MyClass3();
	}
}
