package soundsystem;

public class MyClass {
	private int callCount = 0;
	private MediaPlayer player;
	
	public MyClass(MediaPlayer player) {
		this.player = player;
	}
	
	public void foo() {
		callCount++;
		System.out.println("MyClass callCount: "+callCount);
		player.play();
	}
}
