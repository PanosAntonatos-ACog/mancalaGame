package assessment.bol.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class IllegalMoveException extends Exception {
    private String body;

    public IllegalMoveException() {
        this.body = "You cheeky individual";
    }
}
