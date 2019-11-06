package tacos;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Movie implements Performance{

	private PrintStream out;
	
	@Autowired
	public Movie(PrintStream out) {
		this.out = out;
	}
	
	@Override
	public void perform() {
		// TODO Auto-generated method stub
		out.println("Movie is performing!");
	}

	public void report(String name) {
		// TODO Auto-generated method stub
		out.println("Movie "+name+" is performing!");
	}

}
