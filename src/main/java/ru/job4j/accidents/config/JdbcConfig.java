package ru.job4j.accidents.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

/**
 * @Configuration
 *
 * use autoconfigure springboot
 */
@EnableTransactionManagement
public class JdbcConfig {

    @Bean
    public DataSource ds(@Value("${db.jdbc.driver}") String driver,
                         @Value("${db.jdbc.url}") String url,
                         @Value("${db.jdbc.username}") String username,
                         @Value("${db.jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
