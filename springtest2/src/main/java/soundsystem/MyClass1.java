package soundsystem;

public class MyClass1 {
	private int callCount = 0;
	
	public void foo() {
		callCount++;
		System.out.println("This is MyClass1, callCount: "+callCount);
	}
}
