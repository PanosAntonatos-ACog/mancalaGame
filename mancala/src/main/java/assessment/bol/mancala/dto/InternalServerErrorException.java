package assessment.bol.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class InternalServerErrorException extends Exception {
    private String body;

    public InternalServerErrorException() {
        this.body = "Oops that's embarrassing...";
    }
}
