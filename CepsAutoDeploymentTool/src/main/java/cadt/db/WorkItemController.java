package cadt.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cadt.data.WorkItemRepository;
import cadt.domain.WorkItem;
import cadt.domain.WorkItemDB;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/db",produces="application/json")
@CrossOrigin(origins="*")
public class WorkItemController {

	private final WorkItemRepository workItemRepository;
	
	@Autowired
	public WorkItemController(WorkItemRepository workItemRepository) {
		this.workItemRepository = workItemRepository;
	}
	
	@GetMapping(path="/workitems")
	public Flux<WorkItemDB> allWorkitems(){
		return workItemRepository.findAll();
	}
	
	@GetMapping(path="/workitems/status/{status}")
	public Flux<WorkItemDB> getWorkitemsByStatus(@PathVariable("status") String status){
		return workItemRepository.findByStatus(status);
	}
	
	@GetMapping(path="/workitem/{id}")
	public Mono<WorkItemDB> getWorkItemById(@PathVariable("id") String id){
		return workItemRepository.findById(id);
	}
	
	@PostMapping(path="/workitem",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<WorkItemDB> postWorkItem(@RequestBody WorkItemDB workitem){
		System.out.println("CreateWorkItem");
		return workItemRepository.save(workitem);
	}
	
	@DeleteMapping(path="/delete-workitem/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public Mono<Void> deleteWorkItem(@PathVariable("id") String id){
		System.out.println("DeleteWorkItem");
		return workItemRepository.deleteById(id);
	}
}
