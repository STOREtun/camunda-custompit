package custompit.ui.dto;

import java.util.List;

/**
 * Class used to return response from API calls
 */
public class ApiResponse {
    private final List<ActivityDto> activities;
    private final String errorMessage;

    /**
     * Constructor used to return an OK response with a list of activities
     * @param activities the found activities
     */
    public ApiResponse(List<ActivityDto> activities){
        this.activities = activities;
        this.errorMessage = null;
    }

    /**
     * Constructor used to return an error message
     * @param error the error to display to the user
     */
    public ApiResponse(String error){
        this.activities = List.of();
        this.errorMessage = error;
    }

    public List<ActivityDto> getActivities() {
        return activities;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
