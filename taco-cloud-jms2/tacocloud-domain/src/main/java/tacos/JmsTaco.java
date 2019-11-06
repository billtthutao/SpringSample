package tacos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
public class JmsTaco implements Serializable{

	private static final long serialVersionUID = 1L;

	private final String name;
  
	private final Date createdAt;

	private final List<JmsIngredient> ingredients;
}
