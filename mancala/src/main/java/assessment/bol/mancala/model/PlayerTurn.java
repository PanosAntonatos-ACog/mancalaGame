package assessment.bol.mancala.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerTurn implements Serializable {

    private String playerToMoveId;

}
