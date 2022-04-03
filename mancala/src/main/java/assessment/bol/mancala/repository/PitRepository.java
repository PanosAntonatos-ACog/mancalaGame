package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.Pit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface PitRepository extends MongoRepository<Pit, Integer> {
}
