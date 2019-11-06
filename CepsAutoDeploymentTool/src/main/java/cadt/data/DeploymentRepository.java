package cadt.data;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import cadt.domain.Deployment;

public interface DeploymentRepository extends ReactiveCassandraRepository<Deployment, String>{
	
}
