package concert;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import concert.Movie;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/sample.xml");
		
		Performance movie = context.getBean(Performance.class);
		movie.perform();
		
		context.close();
	}
}
