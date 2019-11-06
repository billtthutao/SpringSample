package tacos.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import tacos.User;
import tacos.resttemplate.UserServiceClient;

@Component
public class TestUserHealthIndicator implements HealthIndicator{

private final UserServiceClient userServiceClient;
	
	public TestUserHealthIndicator(UserServiceClient userServiceClient) {
		this.userServiceClient = userServiceClient;
	}
	
	@Override
	public Health health() {
		// TODO Auto-generated method stub
		try {
			User user = userServiceClient.getUserById(1);
			if(user == null) {
				throw new Exception();
			}
		}catch(Exception e) {
			return Health
					.outOfService()
					.withDetail("reason",
					"I'm out of service")
					.withDetail("path", "/api/allUsers")
					.build();
			
		}
		
		return Health
				.up()
				.withDetail("reason", "All is good!")
				.build();
	}

}
