package custompit.camunda.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        // todo do actual check against DB etc
        return new ResponseEntity<>("I am healthy!", HttpStatus.OK);
    }
}
