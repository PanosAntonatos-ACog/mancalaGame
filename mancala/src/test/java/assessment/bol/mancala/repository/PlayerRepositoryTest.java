package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class PlayerRepositoryTest {


    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void testRepository() throws Exception {
        this.playerRepository.deleteAll();

        Player saved = this.playerRepository.save(new Player());

        Assert.assertNotNull(saved.getId());

        List<Player> list = this.playerRepository.findAll();

        Assert.assertEquals(1, list.size());
    }

}
