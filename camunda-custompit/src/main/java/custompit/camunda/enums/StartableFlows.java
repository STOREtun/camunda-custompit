package custompit.camunda.enums;

public enum StartableFlows {
    MASTER("master_flow");

    private final String flowKey;

    StartableFlows(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getFlowKey(){
        return this.flowKey;
    }
}
