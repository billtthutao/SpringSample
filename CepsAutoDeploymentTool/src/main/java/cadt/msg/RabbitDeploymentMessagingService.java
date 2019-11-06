package cadt.msg;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cadt.domain.Deployment;

@Service
public class RabbitDeploymentMessagingService implements DeploymentMessagingService{

	  private RabbitTemplate rabbit;
	  
	  @Autowired
	  public RabbitDeploymentMessagingService(RabbitTemplate rabbit) {
	    this.rabbit = rabbit;
	  }
	  
	  //create the exchange (direct) "cadtcloud.deployments" and queue "cadtcloud.deployment.queue" in rabbitmq first, 
	  //and bind the queue to the exchange.
	  
	  public void sendDeployment(Deployment deployment, String source) {
		  System.out.println("rabbit send deployment");
		  rabbit.convertAndSend("cadtcloud.deployments","cadtcloud.deployment.queue", deployment,
		        new MessagePostProcessor() {
		          @Override
		          public Message postProcessMessage(Message message)
		              throws AmqpException {
		            MessageProperties props = message.getMessageProperties();
		            props.setHeader("X_DEPLOYMENT_SOURCE", source);
		            return message;
		          } 
		        });
	  }
}
