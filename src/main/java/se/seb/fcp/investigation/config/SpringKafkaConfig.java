package se.seb.fcp.investigation.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import se.seb.fcp.investigation.constant.ApplicationConstant;
import se.seb.fcp.investigation.domain.Alert;

@Configuration
@EnableKafka
public class SpringKafkaConfig {

	@Bean
	public ProducerFactory<String, Object> masurProducerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstant.KAFKA_LOCAL_SERVER_CONFIG);
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "se.seb.fcp.investigation.domain");
		return new DefaultKafkaProducerFactory<String, Object>(configMap);
	}

	@Bean
	public ProducerFactory<String, Object> rtProducerFactory() {

		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstant.KAFKA_LOCAL_SERVER_CONFIG);
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "se.seb.fcp.investigation.domain");
		return new DefaultKafkaProducerFactory<String, Object>(configMap);
	}
	@Bean
	public ProducerFactory<String, Object> screeningProducerFactory() {

		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstant.KAFKA_LOCAL_SERVER_CONFIG);
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "se.seb.fcp.investigation.domain");
		return new DefaultKafkaProducerFactory<String, Object>(configMap);
	}

	@Bean(name = "masur")
	public KafkaTemplate<String, Object> masurKafkaTemplate() {
		return new KafkaTemplate<>(masurProducerFactory());
	}

	@Bean(name = "rt")
	public KafkaTemplate<String, Object> rtKafkaTemplate() {
		return new KafkaTemplate<>(rtProducerFactory());
	}

	@Bean(name = "screening")
	public KafkaTemplate<String, Object> screeningKafkaTemplate() {
		return new KafkaTemplate<>(screeningProducerFactory());
	}

	@Bean
	public ConsumerFactory<String, Alert> consumerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstant.KAFKA_LOCAL_SERVER_CONFIG);
		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, ApplicationConstant.GROUP_ID_JSON);
		configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "se.seb.fcp.investigation.domain");
		return new DefaultKafkaConsumerFactory<>(configMap);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Alert> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Alert> factory = new ConcurrentKafkaListenerContainerFactory<String, Alert>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
									   MongoMappingContext context) {

		MappingMongoConverter converter =
				new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

		return mongoTemplate;

	}
}