package cadt;

import org.springframework.stereotype.Component;

@Component
public class Student implements Person{

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("Student say");
	}

}
