package cadt.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cadt.domain.EmailRequest;

public class T6 {

	public static void main(String[] args) {
		
	    RestTemplate rest = new RestTemplate();
	    String[] users = {"billtt@163.com","taohu@cn.ibm.com"};
	    String[] cc = {"billtt@163.com","taohu@cn.ibm.com"};
	    EmailRequest email = new EmailRequest(users,cc,"TemplateEmail","<html><body><h3><font color=\\\"blue\\\">This is a html email from springboot.</font></h3></body></html>");
	    ResponseEntity<EmailRequest> responseEntity =
	    		rest.postForEntity("http://localhost:8080/email/send/templateEmail",
	    		email,
	    		EmailRequest.class);
	}
}
