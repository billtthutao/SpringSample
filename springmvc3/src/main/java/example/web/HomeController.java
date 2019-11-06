package example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class HomeController {
	
	@RequestMapping(value="/", method=GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/index", method=GET)
	public String index() {
		return "index";
	}
}
