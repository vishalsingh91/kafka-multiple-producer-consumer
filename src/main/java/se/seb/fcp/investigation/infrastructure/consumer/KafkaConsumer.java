package se.seb.fcp.investigation.infrastructure.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import se.seb.fcp.investigation.constant.ApplicationConstant;
import se.seb.fcp.investigation.domain.Alert;
import se.seb.fcp.investigation.repository.AlertsRepository;

import java.util.Date;
import java.util.UUID;

@Component
public class KafkaConsumer{

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	private AlertsRepository repository;

	@KafkaListener(groupId = ApplicationConstant.GROUP_ID_JSON, topics = ApplicationConstant.TOPIC_NAME, containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void receivedMessage(Alert message) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		message.setCreatedDate(new Date());
		message.setId(UUID.randomUUID());
		createCollection(message);
		String jsonString = mapper.writeValueAsString(message);
		logger.info("Received the alert " + jsonString);
	}

	private void createCollection(Alert message) {
		repository.insert(message);
	}
}
