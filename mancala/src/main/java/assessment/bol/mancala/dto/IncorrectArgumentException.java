package assessment.bol.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class IncorrectArgumentException extends Exception {
    private String body;

    public IncorrectArgumentException() {
        this.body = "I have conflicting feelings about it";
    }
}
