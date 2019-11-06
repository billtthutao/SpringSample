package cadt.db;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cadt.data.DeploymentRepository;
import cadt.domain.Deployment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/db",produces="application/json")
@CrossOrigin(origins="*")
public class DeploymentController {

	private final DeploymentRepository deploymentRepository;
	
	public DeploymentController(DeploymentRepository deploymentRepository) {
		
		this.deploymentRepository = deploymentRepository;
	}
	
	@GetMapping(path="/deployments")
	public Flux<Deployment> allDeployments(){
		return deploymentRepository.findAll();
	}
	
	@GetMapping(path="/deployment/{id}")
	public Mono<Deployment> getDeploymentById(@PathVariable("id") String id){
		return deploymentRepository.findById(id);
	}
	
	@PostMapping(path="/deployment",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Deployment> postWorkItem(@RequestBody Deployment deployment){
		System.out.println("CreateDeployment");
		return deploymentRepository.save(deployment);
	}
}
