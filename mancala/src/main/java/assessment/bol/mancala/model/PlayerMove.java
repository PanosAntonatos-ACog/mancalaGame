package assessment.bol.mancala.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Document("player_moves")
@Data
public class PlayerMove implements Serializable {

    @Id
    private String id;
    private Pit pit;

    public PlayerMove(Pit pit) {
        this.pit = pit;
    }
}
