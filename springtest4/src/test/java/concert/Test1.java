package concert;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import concert.ConcertConfig;
import concert.Movie;

public class Test1 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConcertConfig.class);
		
		context.getBean(Movie.class).perform();
		
		context.close();
	}
}
