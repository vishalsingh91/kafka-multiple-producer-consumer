package se.seb.fcp.investigation.infrastructure.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.seb.fcp.investigation.constant.ApplicationConstant;
import se.seb.fcp.investigation.domain.Alert;

@RestController
@RequestMapping("/produce")
public class KafkaProducer {

    @Autowired
    @Qualifier("masur")
    private KafkaTemplate<String, Object> masurKafkaTemplate;

    @Autowired
    @Qualifier("rt")
    private KafkaTemplate<String, Object> rtKafkaTemplate;

    @Autowired
    @Qualifier("screening")
    private KafkaTemplate<String, Object> screeningKafkaTemplate;


    @PostMapping("/message")
    public String sendMessage(@RequestBody Alert message) {

        try {
            masurKafkaTemplate.send(ApplicationConstant.TOPIC_NAME, message);
            rtKafkaTemplate.send(ApplicationConstant.TOPIC_NAME, message);
            screeningKafkaTemplate.send(ApplicationConstant.TOPIC_NAME, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json message sent successfully";
    }

}
