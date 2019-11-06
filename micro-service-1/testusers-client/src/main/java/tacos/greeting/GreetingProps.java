package tacos.greeting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Data;

@Profile("resttemplate")
@ConfigurationProperties(prefix="greeting")
@Component
@Data
public class GreetingProps {
	
	private String message;
	private String anotherMessage;
}
