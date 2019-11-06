package cadt.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import cadt.domain.EmailRequest;

@RestController
@RequestMapping(path="/email",produces="application/json")
@CrossOrigin(origins="*")
public class EmailController {

	@Autowired
	private final MailService mailService;
	
    @Autowired
    private TemplateEngine templateEngine;
    
	public EmailController(MailService mailService) {
		this.mailService = mailService;
	}
	
	@PostMapping(path="/send/simpleEmail",consumes="application/json")
	public EmailRequest sendSimpleMail(@RequestBody EmailRequest emailRequest) {
		mailService.sendSimpleMail(emailRequest.getReceiver(), emailRequest.getCc(), emailRequest.getSubject(), emailRequest.getBody());
		return emailRequest;
	}
	
	@PostMapping(path="/send/htmlEmail",consumes="application/json")
	public EmailRequest sendHtmlMail(@RequestBody EmailRequest emailRequest) {
		mailService.sendHtmlMail(emailRequest.getReceiver(), emailRequest.getCc(), emailRequest.getSubject(), emailRequest.getBody());
		return emailRequest;
	}
	
	@PostMapping(path="/send/templateEmail",consumes="application/json")
	public EmailRequest sendTemplateMail(@RequestBody EmailRequest emailRequest) {
		
		Context context = new Context();
	    context.setVariable("id", "001");
	    String emailContent = templateEngine.process("temp", context);
		mailService.sendHtmlMail(emailRequest.getReceiver(), emailRequest.getCc(), emailRequest.getSubject(), emailContent);
		return emailRequest;
	}
}
