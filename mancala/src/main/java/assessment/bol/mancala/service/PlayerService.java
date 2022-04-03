package assessment.bol.mancala.service;

import assessment.bol.mancala.model.Player;
import assessment.bol.mancala.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public void save(Player player) {
        playerRepository.save(player);
    }

    public Player initPlayer(String playerName, Integer orderOfPlayer) {
        Player newPlayer = new Player(playerName, orderOfPlayer);
        save(newPlayer);
        return newPlayer;
    }
}
