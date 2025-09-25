package custompit.enums;

public enum FlowVariable {
    FINAL_STATEMENT("FINAL STATEMENT");

    private final String variableName;

    FlowVariable(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return this.variableName;
    }
}
