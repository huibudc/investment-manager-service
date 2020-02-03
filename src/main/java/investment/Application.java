package investment;

import investment.config.DBConfigLoader;
import investment.config.PropertiesConfigLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("investment.foundation.dao")
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final DBConfigLoader dbConfigLoader;

    @Autowired
    public Application(DBConfigLoader dbConfigLoader) {
        this.dbConfigLoader = dbConfigLoader;
    }

    public static void main(String[] args) {
        PropertiesConfigLoader.loadProperties();
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dbConfigLoader.investFoundations();
    }
}