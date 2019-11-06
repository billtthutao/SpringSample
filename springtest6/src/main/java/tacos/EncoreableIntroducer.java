package tacos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncoreableIntroducer {
	
	@DeclareParents(value="tacos.Movie",defaultImpl=DefaultEncoreable.class)
	public static Encoreable encoreable;
}
