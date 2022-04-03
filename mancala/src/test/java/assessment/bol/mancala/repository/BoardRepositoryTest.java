package assessment.bol.mancala.repository;

import assessment.bol.mancala.model.Board;
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
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testRepository() throws Exception {
        this.boardRepository.deleteAll();

        Board saved = this.boardRepository.save(new Board());

        Assert.assertNotNull(saved.getId());

        List<Board> list = this.boardRepository.findAll();

        Assert.assertEquals(1, list.size());
    }
}
