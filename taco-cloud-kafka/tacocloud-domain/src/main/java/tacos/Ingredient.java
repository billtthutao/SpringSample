package tacos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient implements Serializable{  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	private final String id;
	private final String name;
	private final Type type;
	
  public static enum Type {
	WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
 }
}

//@Data
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
//@Entity
//public class Ingredient {  
	
//	@Id 
//	private String id;
//	private String name;
//	private Type type;
	
//  public Ingredient(String id, String name, Type type) {
//	  this.id = id;
//	  this.name = name;
//	  this.type = type;
//  }
  
//  public static enum Type {
//	WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
// }
//}

