package assessment.bol.mancala.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Document("boards")
@Data
public class Board implements Serializable {

    @Id
    private String id;
    private List<Pit> pits;
    private List<Player> players;
    private PlayerTurn playerTurn;
    private Integer currentPitIndex;

}
