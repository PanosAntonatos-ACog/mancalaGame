package assessment.bol.mancala.service;

import assessment.bol.mancala.dto.IllegalMoveException;
import assessment.bol.mancala.dto.ResourceNotFoundException;
import assessment.bol.mancala.model.Board;
import assessment.bol.mancala.model.Player;
import assessment.bol.mancala.model.PlayerTurn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Utils {

    public void checkMove(Integer pitPosition) throws IllegalMoveException {
        if (pitPosition == null || pitPosition.compareTo(1) < 0 || pitPosition.compareTo(14) > 0 || pitPosition.compareTo(7) == 0) {
            throw new IllegalMoveException();
        }
    }

    public void checkHousePit(Board board, Integer pitPosition) throws IllegalMoveException {
        if (pitPosition.compareTo(7) == 0 || pitPosition.compareTo(14) == 0) {
            throw new IllegalMoveException("You cannot move the house pit's stones");
        }
    }

    public void checkPlayerTurn(Board board, Integer pitPosition) throws ResourceNotFoundException, IllegalMoveException {
        String currentPlayerId = board.getPlayerTurn().getPlayerToMoveId();
        Player playerToPlay = board.getPlayers().stream().filter(player -> player.getId().equals(currentPlayerId)).findFirst().orElseThrow(ResourceNotFoundException::new);
        if (playerToPlay.getPlayingOrder().equals(1) && pitPosition > 7 ||
                playerToPlay.getPlayingOrder().equals(2) && pitPosition < 7) {
            throw new IllegalMoveException("Stop meddling with your opponents stones, it's unsportsmanlike");
        }
    }

    public PlayerTurn changePlayerTurn(Board currentStateOfBoard) throws ResourceNotFoundException {
        String currentPlayerId = currentStateOfBoard.getPlayerTurn().getPlayerToMoveId();
        Player playerToPlay = currentStateOfBoard.getPlayers().stream().filter(player -> !player.getId().equals(currentPlayerId)).findFirst().orElseThrow(ResourceNotFoundException::new);
        PlayerTurn nextTurn = new PlayerTurn();
        nextTurn.setPlayerToMoveId(playerToPlay.getId());
        return nextTurn;
    }
}
