package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board, String> {
}
