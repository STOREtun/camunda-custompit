package custompit.camunda.rest;

import custompit.camunda.enums.StartableFlows;
import custompit.camunda.service.CamundaStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class CamundaController {

    private final CamundaStarter camundaStarter;

    @Autowired
    public CamundaController(CamundaStarter camundaStarter){
        this.camundaStarter = camundaStarter;
    }

    @GetMapping("/camunda/start/master/{businessKey}")
    public ResponseEntity<String> startMasterFlow(@PathVariable String businessKey){
        String processId = camundaStarter.startNewCamundaFlow(StartableFlows.MASTER, businessKey);
        String responseMessage = String.format("Started Process with id %s", processId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
