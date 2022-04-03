package assessment.bol.mancala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableCaching
@SpringBootApplication
public class MancalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MancalaApplication.class, args);
    }
}
