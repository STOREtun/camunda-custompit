package custompit.rest;

import camundajar.impl.scala.Predef;
import custompit.enums.StartableFlows;
import custompit.service.CamundaStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class CamundaController {

    private final CamundaStarter camundaStarter;

    @Autowired
    public CamundaController(CamundaStarter camundaStarter){
        this.camundaStarter = camundaStarter;
    }

    @GetMapping("/camunda/start/master")
    public ResponseEntity<String> startMasterFlow(){
        String processId = camundaStarter.startNewCamundaFlow(StartableFlows.MASTER);
        String responseMessage = String.format("Started Process with id %s", processId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
