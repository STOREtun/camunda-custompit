package custompit.camunda.enums;

public enum FlowVariable {
    FINAL_STATEMENT("FINAL_STATEMENT");

    private final String variableName;

    FlowVariable(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return this.variableName;
    }
}
