package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer{
	
	private CompactDisc cd;
	private Dessert dst;
	
	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
		System.out.println("Initialized with "+cd.toString());
	}
	
	@Autowired
	@Qualifier("sgtPeppers")
	public void setCompactDisc(CompactDisc cd) {
		this.cd = cd;
		System.out.println("Setter with "+cd.toString());
	}
	
	@Autowired
	@Qualifier("myPrimaryCD")
	public void testMethod1(CompactDisc cd) {
		this.cd = cd;
		System.out.println("testMethod1 with "+cd.toString());
	}
	
	@Autowired
	@Cold
	@Eat
	public void setDessert(Dessert dst) {
		this.dst = dst;
		System.out.println("Setter with dessert "+dst.toString());
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		cd.play();
	}
}
