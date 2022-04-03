package assessment.bol.mancala.service;

import assessment.bol.mancala.dto.IllegalMoveException;
import assessment.bol.mancala.dto.InternalServerErrorException;
import assessment.bol.mancala.dto.ResourceNotFoundException;
import assessment.bol.mancala.model.Board;
import assessment.bol.mancala.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final Utils utils;
    private final PitService pitService;
    private final PlayerService playerService;
    private final PlayerMoveService playerMoveService;
    private final BoardService boardService;


    @Autowired
    public GameService(BoardService boardService, PlayerService playerService, PitService pitService, PlayerMoveService playerMoveService, Utils utils) {
        this.boardService = boardService;
        this.pitService = pitService;
        this.playerService = playerService;
        this.playerMoveService = playerMoveService;
        this.utils = utils;
    }

    public Board initializeGame(String playerName) throws InternalServerErrorException {

        Board newBoard = boardService.initBoard(playerName);
        boardService.save(newBoard);
        return newBoard;
    }

    public Board addSecondPlayer(String boardId, String secondPLayerName) throws ResourceNotFoundException {
        Board board = boardService.getById(boardId);
        List<Player> players = board.getPlayers();
        Player newPlayer = playerService.initPlayer(secondPLayerName, 2);
        players.add(newPlayer);
        board.setPlayers(players);
        boardService.update(board);
        return board;
    }

    public Board nextMove(String boardId, Integer pitPosition) throws IllegalMoveException, ResourceNotFoundException {

        utils.checkMove(pitPosition);
        Board board = boardService.loadGame(boardId);
        utils.checkHousePit(board, pitPosition);
        utils.checkPlayerTurn(board, pitPosition);
        board = boardService.nextMove(board, pitPosition);
        board = boardService.update(board);
        return board;
    }


    public Board loadGame(String id) throws ResourceNotFoundException {
        return boardService.loadGame(id);
    }
}
