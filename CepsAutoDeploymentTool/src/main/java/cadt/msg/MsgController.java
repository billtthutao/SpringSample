package cadt.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cadt.domain.Deployment;

@RestController
@RequestMapping(path="/msg",produces="application/json")
@CrossOrigin(origins="*")
public class MsgController {

	private final DeploymentMessagingService deploymentmMessagingService;
	
	@Autowired
	public MsgController(DeploymentMessagingService deploymentmMessagingService) {
		this.deploymentmMessagingService = deploymentmMessagingService;
	}
	
	@PostMapping(path="/send/deployment",consumes="application/json")
	public Deployment sentDeploymentMsg(@RequestBody Deployment deployment) {
		deploymentmMessagingService.sendDeployment(deployment, "CadtMsgApi");
		return deployment;
	}
}
