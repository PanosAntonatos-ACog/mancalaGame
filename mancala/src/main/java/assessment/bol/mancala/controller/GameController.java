package assessment.bol.mancala.controller;

import assessment.bol.mancala.dto.IllegalMoveException;
import assessment.bol.mancala.dto.InternalServerErrorException;
import assessment.bol.mancala.dto.ResourceNotFoundException;
import assessment.bol.mancala.model.Board;
import assessment.bol.mancala.service.GameService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/game")
@Tag(name = "Mancala game API. Set of endpoints for Creating and Sowing the Game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PutMapping(path = "initializeGame")
    @Operation(summary = "Initialize the game", description = "Endpoint for creating new game instance. It returns a Board object with unique Id used for each turn of the game", method = "PUT")
    public ResponseEntity<Board> initializeGame(@Param(value = "The name of the first player joining, required") @RequestParam(value = "playerName") String playerName) throws InternalServerErrorException {
        return ResponseEntity.ok(gameService.initializeGame(playerName));
    }

    @PutMapping(path = "addSecondPlayer")
    @Operation(summary = "Add second player in the game", description = "Endpoint for creating the second player", method = "PUT")
    public ResponseEntity<Board> addSecondPlayer(@Param(value = "The id of the board, required") @RequestParam(value = "boardId") String boardId,
                                                 @Param(value = "The name of the second player joining, required") @RequestParam(value = "secondPlayerName") String secondPlayerName)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(gameService.addSecondPlayer(boardId, secondPlayerName));
    }

    @PostMapping(path = "nextMove")
    @Operation(summary = "Next move", description = "Player does the next move of the game", method = "POST")
    public ResponseEntity<Board> nextMove(@Param(value = "The id of the board, required") @RequestParam(value = "boardId") String boardId,
                                          @Param(value = "The id of pit, required") @RequestParam(value = "pitPosition") Integer pitPosition)
            throws IllegalMoveException, ResourceNotFoundException {
        return ResponseEntity.ok(gameService.nextMove(boardId, pitPosition));
    }

    @GetMapping("{id}")
    @Operation(summary = "Get game by id", description = "Get the current state of the game by ID if exists, otherwise returns resource not found", method = "GET")
    public ResponseEntity<Board> getGame(
            @Param(value = "The id of game created by calling createGame() method. It's an String e.g. 5d34968590fcbd35b086bc21. It can't be empty or null")
            @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(gameService.loadGame(id));
    }

}
