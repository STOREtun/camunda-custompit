package ui.rest

import custompit.ui.ActivityType
import custompit.ui.dto.ActivityDto
import custompit.ui.dto.ApiResponse
import custompit.ui.rest.CamundaActivityController
import custompit.ui.service.ActivityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.mockito.Mockito
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class CamundaActivityControllerSpec extends Specification{

    private Timestamp mockedTimestamp = Timestamp.valueOf(LocalDateTime.now())

    void "Verify that internal server error is returned on error"(){
        given: "The rest controller to find activities with a service that always throws an exception"
        ActivityService mockService = Mockito.mock(ActivityService.class)
        Mockito.when(mockService.getActivitiesWithBusinessKey(Mockito.anyString())).thenThrow(new NullPointerException())
        CamundaActivityController controller = new CamundaActivityController(mockService)

        when: "The rest endpoint is called"
        ResponseEntity<ApiResponse> response = controller.findProcessesFromBusinessKey("somekey")

        then: "An internal server error is returned"
        assert response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR
        assert response.getBody().getErrorMessage() == ("An error happened, check the log once the todo is fixed")
    }

    void "Verify that 200 OK is returned"(){
        given: "The rest controller to find activities"
        ActivityService mockService = Mockito.mock(ActivityService.class)
        Mockito.when(mockService.getActivitiesWithBusinessKey(Mockito.anyString())).thenReturn(createOKMockResponse())
        CamundaActivityController controller = new CamundaActivityController(mockService)

        when: "The rest endpoint is called"
        ResponseEntity<ApiResponse> response = controller.findProcessesFromBusinessKey("somekey")

        then: "The mocked entity found by the service is returned in an ApiResponse class"
        assert response.getStatusCode() == HttpStatus.OK
        ApiResponse apiResponse = response.getBody()
        assert apiResponse.getActivities().size() == 1
        assert apiResponse.getActivities().get(0).getType() == ActivityType.CAMUNDA
        assert apiResponse.getActivities().get(0).getActivityId() == "123"
        assert apiResponse.getActivities().get(0).getStartTime() == mockedTimestamp
        assert apiResponse.getActivities().get(0).getDefinitionKey() == "definitionKey"
    }

    private List<ActivityDto> createOKMockResponse(){
        List<ActivityDto> mockResponse = new ArrayList<>()
        ActivityDto dto = new ActivityDto(
                ActivityType.CAMUNDA, "123", mockedTimestamp, "definitionKey"
        )
        mockResponse.add(dto)
        return mockResponse
    }

}
