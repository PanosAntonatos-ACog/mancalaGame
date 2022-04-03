package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.PlayerMove;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface PlayerMoveRepository extends MongoRepository<PlayerMove, Integer> {
}
