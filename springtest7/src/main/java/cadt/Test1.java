package cadt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);
		
		Person student = context.getBean(Student.class);
		
		student.say();
		
		context.getBean(Classroom.class).fun();;
		
		context.close();
	}
}
