package custompit.service;

import custompit.enums.StartableFlows;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamundaStarter {

    RuntimeService runtimeService;

    @Autowired
    public CamundaStarter(RuntimeService runtimeService){
        this.runtimeService = runtimeService;
    }

    public String startNewCamundaFlow(StartableFlows flowToStart){
        ProcessInstance instance = runtimeService.startProcessInstanceById(flowToStart.getFlowId());
        return instance.getProcessInstanceId();
    }
}
