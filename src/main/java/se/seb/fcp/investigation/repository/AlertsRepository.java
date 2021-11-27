package se.seb.fcp.investigation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.seb.fcp.investigation.domain.Alert;

public interface AlertsRepository extends MongoRepository<Alert, Long>{

    Alert findFirstBySystem(String system);


}
