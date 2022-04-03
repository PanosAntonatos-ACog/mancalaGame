package assessment.bol.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class BadRequestException extends Exception {

    private String body;

    public BadRequestException() {
        this.body = "You brought this upon yourself";
    }
}
