package custompit.enums;

public enum StartableFlows {
    MASTER("master_flow");

    private final String flowId;

    StartableFlows(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId(){
        return this.flowId;
    }
}
