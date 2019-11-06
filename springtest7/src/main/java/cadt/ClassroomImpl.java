package cadt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassroomImpl implements Classroom{

	private Person worker1;
	
	@Autowired
	public ClassroomImpl(Person worker1) {
		this.worker1 = worker1;
	}
	
	public void fun() {
		worker1.say();
	}
}
