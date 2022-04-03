package assessment.bol.mancala.service;

import assessment.bol.mancala.model.PlayerMove;
import assessment.bol.mancala.repository.PlayerMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMoveService {

    private final PlayerMoveRepository playerMoveRepository;

    @Autowired
    public PlayerMoveService(PlayerMoveRepository playerMoveRepository) {
        this.playerMoveRepository = playerMoveRepository;
    }


    public void save(PlayerMove playerMover) {
        playerMoveRepository.save(playerMover);
    }

}
