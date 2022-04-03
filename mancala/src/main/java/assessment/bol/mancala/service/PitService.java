package assessment.bol.mancala.service;

import assessment.bol.mancala.dto.InternalServerErrorException;
import assessment.bol.mancala.model.Pit;
import assessment.bol.mancala.repository.PitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PitService {

    private final PitRepository pitRepository;

    @Autowired
    public PitService(PitRepository pitRepository) {
        this.pitRepository = pitRepository;
    }


    public void save(Pit pit) throws InternalServerErrorException {
        pitRepository.save(pit);
    }

    public void saveAll(List<Pit> newPits) {
        pitRepository.saveAll(newPits);
    }

    public List<Pit> intiPits() {
        List<Pit> pits = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            Pit pit = new Pit();
            if (i == 7 || i == 14) {
                pit.setNumberOfStones(0);
                pit.setPitPosition(i);
            } else {
                pit.setNumberOfStones(6);
                pit.setPitPosition(i);
            }
            pits.add(pit);
        }
        saveAll(pits);
        return pits;
    }

}
