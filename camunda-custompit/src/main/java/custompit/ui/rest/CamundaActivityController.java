package custompit.ui.rest;

import custompit.ui.dto.ActivityDto;
import custompit.ui.dto.ApiResponse;
import custompit.ui.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class CamundaActivityController {

    private final ActivityService activityService;

    @Autowired
    public CamundaActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping(value = "/search/{businessKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> findProcessesFromBusinessKey(@PathVariable String businessKey){
        try{
            List<ActivityDto> instances = activityService.getActivitiesWithBusinessKey(businessKey);
            return ResponseEntity.ok(new ApiResponse(instances));
        }catch(Exception e){
            // todo implement logger
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An error happened, check the log once the todo is fixed"));
        }
    }
}
