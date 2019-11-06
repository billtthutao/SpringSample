package tacos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
public class JmsOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final Date placedAt;
	private final String deliveryName;
	private final String deliveryStreet;
	private final String deliveryCity;
	private final String deliveryState;
	private final String deliveryZip;

	private final List<JmsTaco> tacos;
}
