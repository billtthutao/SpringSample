package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Configuration
@ComponentScan
@ImportResource("classpath:sample.xml")
public class CDPlayerConfig {
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public MyClass1 my1() {
		return new MyClass1();
	}
	
	@Bean
	public MyClass2 my2() {
		return new MyClass2(my1());
	}
}
