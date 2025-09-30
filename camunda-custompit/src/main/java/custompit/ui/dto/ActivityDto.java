package custompit.ui.dto;

import custompit.ui.ActivityType;

import java.sql.Timestamp;

public class ActivityDto {
    private ActivityType type;
    private String activityId;
    private Timestamp startTime;
    private String definitionKey; // camunda definition or kafka topic

    public ActivityDto(ActivityType type,
                       String activityId,
                       Timestamp startTime,
                       String definitionKey) {
        this.type = type;
        this.activityId = activityId;
        this.startTime = startTime;
        this.definitionKey = definitionKey;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getDefinitionKey() {
        return definitionKey;
    }

    public void setDefinitionKey(String definitionKey) {
        this.definitionKey = definitionKey;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
