package custompit.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class ChildFlowFacade {

    public void processMasterRequest(DelegateExecution execution){
        // todo implement logic
    }

    public void setFinalStatement(DelegateExecution execution){

    }

}
