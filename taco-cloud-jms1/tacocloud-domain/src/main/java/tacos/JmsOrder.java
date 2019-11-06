package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JmsOrder implements Serializable{

  private final Date placedAt;
  private final String deliveryName;
  private final String deliveryStreet;
  private final String deliveryCity;
  private final String deliveryState;
  private final String deliveryZip;

  private final List<JmsTaco> tacos = new ArrayList<>();

}
