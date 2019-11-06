package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyClass {
	private int callCount = 0;
	private MediaPlayer player;
	
	@Autowired
	public MyClass(MediaPlayer player) {
		this.player = player;
	}
	
	public void foo() {
		callCount++;
		System.out.println("MyClass callCount: "+callCount);
		player.play();
	}
}
