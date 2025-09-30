package custompit.ui.service;

import custompit.ui.data.repository.CamundaRepository;
import custompit.ui.data.repository.KafkaMessagesRepository;
import custompit.ui.dto.ActivityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final CamundaRepository camundaRepository;
    private final KafkaMessagesRepository kafkaMessagesRepository;

    @Autowired
    public ActivityService(CamundaRepository camundaRepository,
                           KafkaMessagesRepository kafkaMessagesRepository){
        this.camundaRepository = camundaRepository;
        this.kafkaMessagesRepository = kafkaMessagesRepository;
    }

    public List<ActivityDto> getActivitiesWithBusinessKey(String businessKey){
        List<ActivityDto> camundaActivities = camundaRepository.getAllActivitiesWithBusinessKey(businessKey);
        List<ActivityDto> kafkaMessages = kafkaMessagesRepository.getMessagesWithBusinessKey(businessKey);
        camundaActivities.addAll(kafkaMessages);
        return camundaActivities;
    }
}
