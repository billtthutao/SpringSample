package test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test2 {
	  @Test
	  public void myTest1() throws InterruptedException {
		  Flux<Player> playerFlux = Flux
			      .just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
			      .log()
			      .map(p -> {
			    	  			try {
			    	  					TimeUnit.SECONDS.sleep(1);
			    	  			} catch (InterruptedException e) {
			    	  					e.printStackTrace();
			    	  			}
			    	  			String[] split = p.split("\\s");
			    	  			return new Player(split[0], split[1]);
			    	  		})
			      //.subscribeOn(Schedulers.parallel())
			      //.delayElements(Duration.ofSeconds(1))
			      .map(e -> {System.out.println("Other transformation");return e;});
			     
		  System.out.println("main is done");	
		  
		  playerFlux.subscribe(e -> Logger.info(e));
		  
		  TimeUnit.SECONDS.sleep(5);
	  }
	  
	  @Test
	  public void myTest2() throws InterruptedException {
		  Flux
			      .just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
			      .log()
			      .flatMap(p -> {
			    	  			String[] split = p.split("\\s");
			    	  			return Flux.just(new Player(split[0], split[1]))
			    	  					   .delayElements(Duration.ofSeconds(1))
			    	  					   .subscribeOn(Schedulers.parallel())
			    	  					;
			    	  		})
			      //.subscribeOn(Schedulers.parallel())
			      .subscribe(e -> Logger.info(e));
			 
		  System.out.println("main is done");
		  TimeUnit.SECONDS.sleep(5);
	  }
	  
	  @Data
	  private static class Player {
	    private final String firstName;
	    private final String lastName;
	    
	    public Player(String firstName, String lastName) {
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    }

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}
		
		public String toString() {
			return firstName+lastName;
		}
		
		public boolean equals(Object o) {
			Player player = (Player) o;
			return firstName.equals(player.getFirstName()) && lastName.equals(player.getLastName());
		}
		
		public int hashCode() {
			return firstName.hashCode() + lastName.hashCode();
		}
	  }
	  
	  public static class Logger{
		  public static void info(Player player) {
			  System.out.println(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date())+":"+ Thread.currentThread()+"***"+player);
		  }
	  }
}
