package assessment.bol.mancala.cdc;

import assessment.bol.mancala.controller.GameController;
import assessment.bol.mancala.model.*;
import assessment.bol.mancala.service.BoardService;
import assessment.bol.mancala.service.GameService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/*
    This is used for Spring Contact Testing.  It prepares the Stub dependencies
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@Import({GameController.class, GameService.class})
@DirtiesContext
public class BaseClass {

    @LocalServerPort
    private String port;

    @MockBean
    private GameService gameService;

    @MockBean
    private BoardService boardService;

    @Autowired
    private GameController gameController;

    @Before
    public void setupGame() throws Exception {
        RestAssured.baseURI = "http://localhost:" + this.port;

        String playerName = "Will Smith";
        int orderOfPlayer = 1;
        PlayerMove playerMove = new PlayerMove();
        Player newPlayer = new Player("6240afe39f3cc503ff362e48", playerName, List.of(playerMove), orderOfPlayer);
        PlayerTurn playerTurn = new PlayerTurn("6240afe39f3cc503ff362e49");
        List<Pit> pits = List.of(
                new Pit("6240afe39f3cc503ff362e3a", 6, 1),
                new Pit("6240afe39f3cc503ff362e3b", 6, 2),
                new Pit("6240afe39f3cc503ff362e3c", 6, 3),
                new Pit("6240afe39f3cc503ff362e3d", 6, 4),
                new Pit("6240afe39f3cc503ff362e3e", 6, 5),
                new Pit("6240afe39f3cc503ff362e3f", 6, 6),
                new Pit("6240afe39f3cc503ff362e40", 0, 7),
                new Pit("6240afe39f3cc503ff362e41", 6, 8),
                new Pit("6240afe39f3cc503ff362e42", 6, 9),
                new Pit("6240afe39f3cc503ff362e43", 6, 10),
                new Pit("6240afe39f3cc503ff362e44", 6, 11),
                new Pit("6240afe39f3cc503ff362e45", 6, 12),
                new Pit("6240afe39f3cc503ff362e46", 6, 13),
                new Pit("6240afe39f3cc503ff362e47", 0, 14)
        );


        Board gameToBeCreated = new Board("6240afe39f3cc503ff362e50", pits, List.of(newPlayer), playerTurn, 0);

        Mockito.when(this.boardService.initBoard("Will Smith"))
                .thenReturn(gameToBeCreated);

        Mockito.when(gameService.loadGame("6240afe39f3cc503ff362e50"))
                .thenReturn(gameToBeCreated);


        RestAssuredMockMvc.standaloneSetup(gameController);
    }
}
