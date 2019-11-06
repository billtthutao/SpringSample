package cadt.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cadt.config.RtcConfig;

public class T5 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RtcConfig.class);
		MyClass1 myclass1 = context.getBean(MyClass1.class);
		myclass1.f();
		context.close();
	}
}
