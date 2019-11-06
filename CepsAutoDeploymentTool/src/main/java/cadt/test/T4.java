package cadt.test;

import org.springframework.beans.factory.annotation.Autowired;

public class T4 {

	private final MyClass1 myclass1;
	
	//@Autowired
	public T4(MyClass1 myclass1) {
		 this.myclass1=myclass1;
	}
	
	public void f() {
		myclass1.f();
	}
}
