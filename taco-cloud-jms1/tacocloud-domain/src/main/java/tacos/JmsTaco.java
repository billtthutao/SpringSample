package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JmsTaco implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private final String name;
  
  private final Date createdAt;

  private final List<JmsIngredient> ingredients = new ArrayList<>();

}
