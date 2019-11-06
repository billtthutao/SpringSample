package concert;

import java.io.PrintStream;

public class Drama implements Performance2{

	private PrintStream out;
	
	public Drama(PrintStream out) {
		this.out = out;
	}
	
	@Override
	public void perform() {
		// TODO Auto-generated method stub
		out.println("Drama is performing!");
	}

}
