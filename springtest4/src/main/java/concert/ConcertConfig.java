package concert;

import java.io.PrintStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
@ImportResource("classpath:META-INF/spring/sample.xml")
public class ConcertConfig {
	
	@Bean
	public PrintStream out() {
		return System.out;
	}
}
