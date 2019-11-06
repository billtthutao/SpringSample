package soundsystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("drink")
@Cold
@Drink
public class ReaTea implements Dessert{
	public String toString() {
		return "ReaTea";
	}
}
