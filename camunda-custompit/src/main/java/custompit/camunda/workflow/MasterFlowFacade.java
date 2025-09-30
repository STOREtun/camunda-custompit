package custompit.camunda.workflow;

import custompit.camunda.enums.FlowVariable;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class MasterFlowFacade {

    public void printFinalStatement(DelegateExecution execution){
        String finalMessage = String.format(
                "The final statement is: %s",
                execution.getVariable(FlowVariable.FINAL_STATEMENT.getVariableName())
        );
        System.out.println(finalMessage);
    }
}
