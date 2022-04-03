package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.PlayerMove;
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
public class PlayerMoveRepositoryTest {

    @Autowired
    PlayerMoveRepository playerMoveRepository;

    @Test
    public void testRepository() throws Exception {
        this.playerMoveRepository.deleteAll();

        PlayerMove saved = this.playerMoveRepository.save(new PlayerMove());

        Assert.assertNotNull(saved.getId());

        List<PlayerMove> list = this.playerMoveRepository.findAll();

        Assert.assertEquals(1, list.size());
    }


}
