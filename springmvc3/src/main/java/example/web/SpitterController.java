package example.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import example.Spitter;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	@RequestMapping(value="/register", method=GET)
	public String showRegistrationForm() {
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=POST)
	public String processRegistration(Spitter spitter, Model model) {
	
		model.addAttribute(spitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}
	
	@RequestMapping(value="/{username}", method=GET)
	public String showSpitterProfile(
	@PathVariable String username, Model model) {
		model.addAttribute("received", username);
		return "profile";
	}
}
