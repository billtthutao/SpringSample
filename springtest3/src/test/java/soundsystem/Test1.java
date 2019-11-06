package soundsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("sample.xml");
		context.getBean(SgtPeppers.class).play();
		context.getBean(MediaPlayer.class).play();
		context.getBean(MyClass.class).foo();
		context.getBean(MyClass.class).foo();
		context.getBean(MyClass1.class).foo();
		context.getBean(MyClass1.class).foo();
		context.getBean(MyClass2.class).foo();
		
		//AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(MyConfig1.class);
		context.getBean(MyClass3.class).play();
		
		context.close();
		//context1.close();
	}
}
