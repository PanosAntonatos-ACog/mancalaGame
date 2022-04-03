package assessment.bol.mancala.controller;

import assessment.bol.mancala.model.Board;
import assessment.bol.mancala.service.BoardService;
import assessment.bol.mancala.service.GameService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureDataMongo
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class GameControllerTest {

    private final Resource newGameJson = new ClassPathResource("initialize_game.json");
//    private final Resource jsonOfKalahaGameSowPit2JustAfterCreation = new ClassPathResource("mancala-sow-2.json");

    @Autowired
    private MockMvc mvc;
    @MockBean
    private GameService gameService;
    @MockBean
    private BoardService boardService;

    @SneakyThrows
    private String asJson(Resource resource) throws Exception {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    @Test
    public void testInitializeGame() throws Exception {

        String playerName = "Will Smith"; // Hope he won't slap me in the face for using his name ^^

        Board gameToBeCreated = this.boardService.initBoard(playerName);

        given(this.gameService.initializeGame(playerName)).willReturn(gameToBeCreated);

        this.mvc.perform(put("/game/initializeGame"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJson(newGameJson), false))
                .andReturn();
    }

}
