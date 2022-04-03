package assessment.bol.mancala.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Document("pits")
@Data
public class Pit implements Serializable {

    @Id
    private String id;
    private int numberOfStones;
    private int pitPosition;


}
