package investment.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;


@Configuration
public class DruidConfig {
    @Bean
    public DataSource druid() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/investment?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword(ConfigLoader.mysqlPassword());
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return druidDataSource;
    }
}

