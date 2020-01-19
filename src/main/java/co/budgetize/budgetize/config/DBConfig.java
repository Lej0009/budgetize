package co.budgetize.budgetize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DBConfig {

    @Value("$jdbc:mysql://localhost:8889/budgetize")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
}



//@Configuration
//public class DBConfig {
//
//    @Bean
//    public DataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//
//        DataSource dataSource = new DataSource();
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//
//        return dataSource;
//    }