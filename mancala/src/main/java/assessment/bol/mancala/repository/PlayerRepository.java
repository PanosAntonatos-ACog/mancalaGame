package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface PlayerRepository extends MongoRepository<Player, Integer> {
}
