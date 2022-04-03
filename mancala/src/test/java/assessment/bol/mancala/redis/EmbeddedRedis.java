package assessment.bol.mancala.redis;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class EmbeddedRedis implements ExitCodeGenerator, DisposableBean {
    @Override
    public void destroy() throws Exception {

    }

    @Override
    public int getExitCode() {
        return 0;
    }

//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    private RedisServer redisServer;
//
//    @PostConstruct
//    public void startRedis() throws IOException {
//        redisServer = RedisServer.builder().port(redisPort).setting("maxmemory 128M") //maxheap 128M
//                .build();
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        redisServer.stop();
//    }
//
//    @Override
//    public int getExitCode() {
//        redisServer.stop();
//        return 0;
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        redisServer.stop();
//    }
}