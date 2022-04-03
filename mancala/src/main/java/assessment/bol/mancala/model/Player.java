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
@Document("players")
@Data
public class Player implements Serializable {

    @Id
    private String id;
    private String playerName;
    private List<PlayerMove> playerMoves;
    private Integer playingOrder;

    public Player(String playerName, Integer playingOrder) {
        this.playerName = playerName;
        this.playingOrder = playingOrder;
    }
}
