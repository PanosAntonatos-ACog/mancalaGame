package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.Pit;
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
public class PitRepositoryTest {

    @Autowired
    PitRepository pitRepository;

    @Test
    public void testRepository() throws Exception {
        this.pitRepository.deleteAll();

        Pit saved = this.pitRepository.save(new Pit());

        Assert.assertNotNull(saved.getId());

        List<Pit> list = this.pitRepository.findAll();

        Assert.assertEquals(1, list.size());
    }


}
