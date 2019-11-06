package tacos;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/minstrel.xml");
		
		Knight knight = context.getBean(Knight.class);
		
		knight.embarkOnQuest();
		
		context.close();
	}

}
