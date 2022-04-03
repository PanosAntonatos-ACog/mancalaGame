package assessment.bol.mancala.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ResourceNotFoundException extends Exception {

    private String body;

    public ResourceNotFoundException() {
        this.body = "We searched really hard, but we came up short";
    }
}
