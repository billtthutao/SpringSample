package soundsystem;

public class MyClass2 {
	private MyClass1 my1;
	
	public MyClass2(MyClass1 my1) {
		this.my1 = my1;
	}
	
	public void foo() {
		System.out.println("This is myClass2");
		my1.foo();
	}
}
