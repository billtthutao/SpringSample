package soundsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
		context.getBean(MediaPlayer.class).play();
		context.getBean(MyClass.class).foo();
		context.getBean(MyClass.class).foo();
		context.getBean(MyClass1.class).foo();
		context.getBean(MyClass2.class).foo();;
		
		context.getBean(MyClass3.class).play();
		
		context.close();
	}
}
