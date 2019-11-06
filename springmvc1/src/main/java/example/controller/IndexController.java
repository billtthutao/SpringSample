package example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model) {
    	model.addAttribute("message", "Hello World");
        return "index";
    }
    
    @RequestMapping(path="/test",method=RequestMethod.GET)
    public String testPara(
    @RequestParam("parm1") int parm1,
    @RequestParam("parm2") String parm2, Model model) {
    	model.addAttribute("parm1",parm1);
    	model.addAttribute("parm2",parm2);
    	return "test";
    }
}
