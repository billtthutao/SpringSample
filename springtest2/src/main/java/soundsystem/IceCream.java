package soundsystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("eat")
@Cold
@Eat
public class IceCream implements Dessert{
	public String toString() {return "IceCream";}
}
