package tacos;

import java.io.PrintStream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {
	private PrintStream out;
	
	@Autowired
	public Audience(PrintStream out) {
		this.out = out;
	}
	
	@Pointcut("execution(* tacos.Performance.perform(..))")
	public void performance() {}
	
	@Before("performance()")
	public void handClap() {
		out.println("Audience is handclapping!");
	}
	
	@Pointcut("execution(* tacos.Performance.report(String)) && args(movieName)")
	public void report(String movieName) {}
	
	@Before("report(movieName)")
	public void handClapWithName(String movieName) {
		out.println("Audience is handclapping for "+movieName+"!");
	}
	
	@Around("report(movieName)")
	public void watchPerformanceWithName(ProceedingJoinPoint jp, String movieName) {
		try {
			out.println("Audience is expecting for "+movieName+"!");
			out.println("Silencing cell phones");
			out.println("Taking seats");
			jp.proceed();
			out.println("CLAP CLAP CLAP!!!");
		} catch (Throwable e) {
			out.println("Demanding a refund");
		}
	}
	
	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			out.println("Silencing cell phones");
			out.println("Taking seats");
			jp.proceed();
			out.println("CLAP CLAP CLAP!!!");
		} catch (Throwable e) {
			out.println("Demanding a refund");
		}
	}
	
}
