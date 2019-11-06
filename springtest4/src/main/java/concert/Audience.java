package concert;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Audience {
	private PrintStream out;
	
	@Autowired
	public Audience(PrintStream out) {
		this.out = out;
	}
	
	public void handClap() {
		out.println("Audience is handclapping!");
	}
	
}
