package assessment.bol.mancala.service;

import assessment.bol.mancala.dto.ResourceNotFoundException;
import assessment.bol.mancala.model.Board;
import assessment.bol.mancala.model.Pit;
import assessment.bol.mancala.model.Player;
import assessment.bol.mancala.model.PlayerTurn;
import assessment.bol.mancala.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final PitService pitService;
    private final PlayerService playerService;
    private final PlayerMoveService playerMoveService;
    private final Utils utils;


    @Autowired
    public BoardService(BoardRepository boardRepository, PlayerService playerService, PitService pitService, PlayerMoveService playerMoveService, Utils utils) {
        this.boardRepository = boardRepository;
        this.pitService = pitService;
        this.playerService = playerService;
        this.playerMoveService = playerMoveService;
        this.utils = utils;
    }

    public Board initBoard(String playerName) {

        Board newBoard = new Board();
        Player newPlayer = playerService.initPlayer(playerName, 1);
        PlayerTurn playerTurn = new PlayerTurn();
        playerTurn.setPlayerToMoveId(newPlayer.getId());

        newBoard.setPits(pitService.intiPits());
        newBoard.setPlayers(List.of(newPlayer));
        newBoard.setPlayerTurn(playerTurn);

        return newBoard;
    }

    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board getById(String boardId) throws ResourceNotFoundException {
        return boardRepository.findById(boardId).orElseThrow(ResourceNotFoundException::new);
    }

    @Cacheable(value = "boards", key = "#id", unless = "#result  == null")
    public Board loadGame(String id) throws ResourceNotFoundException {
        return boardRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @CachePut(value = "boards", key = "#board.id")
    public Board update(Board board) {
        return boardRepository.save(board);
    }

    public Board nextMove(Board board, Integer pitPosition) throws ResourceNotFoundException {

        Pit selectedPit = board.getPits().stream().filter(pit -> pit.getPitPosition() == pitPosition).findFirst().orElseThrow(ResourceNotFoundException::new);

        int stones = selectedPit.getNumberOfStones();
//      Return if the chosen pit has 0 pitstones
        if (stones == 0) return board;

        selectedPit.setNumberOfStones(0);

//      keep the pit index, used for sowing the stones in right pits
        board.setCurrentPitIndex(pitPosition);

//      range over the stones except the last one to sow them to the following pits
        IntStream.range(0, stones - 1).forEach(index -> {
            try {
                sowRight(board, false);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        });


//      sow the last one
        sowRight(board, true);

        int currentPitIndex = board.getCurrentPitIndex();

//      we switch the turn if the last sow was not on any of pit houses (left or right)
        if (currentPitIndex != 7 && currentPitIndex != 14) board.setPlayerTurn(utils.changePlayerTurn(board));

        return board;


    }

    private void sowRight(Board board, Boolean lastStone) throws ResourceNotFoundException {
        int currentPitIndex = board.getCurrentPitIndex() % 14 + 1;

        PlayerTurn playerTurn = board.getPlayerTurn();
        Player playerToPlay = board.getPlayers().stream().filter(player -> player.getId().equals(playerTurn.getPlayerToMoveId())).findFirst().orElseThrow(ResourceNotFoundException::new);

        if ((currentPitIndex == 7 && playerToPlay.getPlayingOrder().equals(2)) || (currentPitIndex == 14 && playerToPlay.getPlayingOrder().equals(1)))
            currentPitIndex = currentPitIndex % 14 + 1;

        board.setCurrentPitIndex(currentPitIndex);

        int finalCurrentPitIndex = currentPitIndex;
        Pit targetPit = board.getPits().stream().filter(pit -> pit.getPitPosition() == finalCurrentPitIndex).findFirst().orElseThrow(ResourceNotFoundException::new);
        if (!lastStone || currentPitIndex == 7 || currentPitIndex == 14) {
            targetPit.setNumberOfStones(targetPit.getNumberOfStones() + 1);
            return;
        }

//      It's the last stone and we need to check the opposite player's pit status
        Pit oppositePit = board.getPits().stream().filter(pit -> pit.getPitPosition() == 14 - finalCurrentPitIndex).findFirst().orElseThrow(ResourceNotFoundException::new);

        /*
        *
        * we are sowing the last stone and the current player's pit is empty but the opposite pit is not empty, therefore,
         we collect the opposite's Pit stones plus the last stone and add them to the House Pit of current player and
         make the opposite Pit empty
        *
        * */

        if (targetPit.getNumberOfStones() == 0 && oppositePit.getNumberOfStones() > 0) {
            int oppositeStones = oppositePit.getNumberOfStones();
            oppositePit.setNumberOfStones(0);
            int pitHouseIndex = currentPitIndex < 7 ? 7 : 14;
            Pit pitHouse = board.getPits().get(pitHouseIndex);
            pitHouse.setNumberOfStones(oppositeStones + 1);
            return;
        }

        targetPit.setNumberOfStones(targetPit.getNumberOfStones() + 1);
    }


}
