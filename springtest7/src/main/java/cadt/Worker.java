package cadt;

import org.springframework.stereotype.Component;

@Component
public class Worker implements Person{

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("Worker say");
	}

}
