package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Configuration
public class RouterFunctionConfig {
	@Autowired
	private final UserController userController;
	
	
	public RouterFunctionConfig(UserController userController) {
		this.userController = userController;
	}
	
	@Bean
	public RouterFunction<?> helloRouterFunction() {
		System.out.println("helloRouterFunction");
		return route(GET("/hello"),request -> ok().body(just("Hello World!"), String.class))
				.andRoute(GET("/api/users"), userController::users)
				;	   
	}
}
