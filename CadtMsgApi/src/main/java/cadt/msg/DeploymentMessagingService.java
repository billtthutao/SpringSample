package cadt.msg;

import cadt.domain.Deployment;

public interface DeploymentMessagingService {

  void sendDeployment(Deployment deployment, String source);
  
}
