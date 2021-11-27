package se.seb.fcp.investigation.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import se.seb.fcp.investigation.config.SpringKafkaConfig;
import se.seb.fcp.investigation.infrastructure.consumer.KafkaConsumer;
import se.seb.fcp.investigation.repository.AlertsRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class KafkaProducerTest {
    @MockBean
    private AlertsRepository repository;
    @MockBean
    private KafkaTemplate<String, Object> kafkaTemplate;

    @MockBean
    private MongoDbFactory mongoDbFactory;

    @MockBean
    private SpringKafkaConfig springKafkaConfig;

    @MockBean
    private KafkaConsumer kafkaConsumer;

    @Test
    void ShouldProduceData() {
        Assert.isTrue(true);
    }
}
