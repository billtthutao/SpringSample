package tacos.web.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tacos.Ingredient;
import tacos.IngredientUDT;
import tacos.Order;
import tacos.PaymentMethod;
import tacos.Taco;
import tacos.TacoUDT;
import tacos.User;
import tacos.UserUDT;
import tacos.data.IngredientRepository;
import tacos.data.PaymentMethodRepository;
import tacos.data.UserRepository;
import tacos.web.api.EmailOrder.EmailTaco;

@Service
public class EmailOrderService {

  private UserRepository userRepo;
  private IngredientRepository ingredientRepo;
  private PaymentMethodRepository paymentMethodRepo;

  public EmailOrderService(UserRepository userRepo, IngredientRepository ingredientRepo,
      PaymentMethodRepository paymentMethodRepo) {
    this.userRepo = userRepo;
    this.ingredientRepo = ingredientRepo;
    this.paymentMethodRepo = paymentMethodRepo;
  }

  public Order convertEmailOrderToDomainOrder(EmailOrder emailOrder) {
    // TODO: Probably should handle unhappy case where email address doesn't match a given user or
    //       where the user doesn't have at least one payment method.
    User user = userRepo.findByEmail(emailOrder.getEmail()).block();
    PaymentMethod paymentMethod = paymentMethodRepo.findByUserUsername(user.getUsername()).block();

    Order order = new Order();
    order.setUser(new UserUDT(user.getUsername(),user.getFullname(),user.getPhoneNumber()));
    order.setCcNumber(paymentMethod.getCcNumber());
    order.setCcCVV(paymentMethod.getCcCVV());
    order.setCcExpiration(paymentMethod.getCcExpiration());
    order.setDeliveryName(user.getFullname());
    order.setDeliveryStreet(user.getStreet());
    order.setDeliveryCity(user.getCity());
    order.setDeliveryState(user.getState());
    order.setDeliveryZip(user.getZip());
    order.setPlacedAt(new Date());

    // TODO: Handle unhappy case where a given ingredient doesn't match
    List<EmailTaco> emailTacos = emailOrder.getTacos();
    for (EmailTaco emailTaco : emailTacos) {
      List<String> ingredientIds = emailTaco.getIngredients();
      List<IngredientUDT> ingredients = new ArrayList<>();
      for (String ingredientId : ingredientIds) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(ingredientId).blockOptional();
        if (optionalIngredient.isPresent()) {
          Ingredient ingredient = optionalIngredient.get();
          ingredients.add(new IngredientUDT(ingredient.getName(),ingredient.getType()));
        }
      }
      
      TacoUDT taco = new TacoUDT(emailTaco.getName(),ingredients);
      order.addDesign(taco);
    }

    return order;
  }

}
