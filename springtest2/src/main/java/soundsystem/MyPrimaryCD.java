package soundsystem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MyPrimaryCD implements CompactDisc {
	private String title = "Title of my primary CD";
	private String artist = "The Bill Hu";

	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}
	
	public String toString() {
		return "MyPrimaryCD";
	}
}

