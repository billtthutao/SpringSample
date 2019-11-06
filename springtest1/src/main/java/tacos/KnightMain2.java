package tacos;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KnightMain2 {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
		
		Knight knight = context.getBean(Knight.class);
		
		knight.embarkOnQuest();
		
		context.close();
	}
}
