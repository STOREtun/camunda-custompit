package custompit.camunda.workflow;

import custompit.camunda.enums.FlowVariable;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class ChildFlowFacade {

    public void processMasterRequest(DelegateExecution execution){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFinalStatement(DelegateExecution execution){
        execution.setVariable(FlowVariable.FINAL_STATEMENT.getVariableName(), "This is the final statement");
    }

}
