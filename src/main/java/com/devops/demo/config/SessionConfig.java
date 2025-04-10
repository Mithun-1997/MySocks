package com.devops.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.sql.DataSource;

@Configuration
@EnableJdbcHttpSession
public class SessionConfig {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public SessionConfig(
        @Qualifier("projectDataSource") DataSource sessionDataSource,  
        @Qualifier("projectTransactionManager") PlatformTransactionManager transactionManager) {
        
        this.jdbcTemplate = new JdbcTemplate(sessionDataSource);  // Using projectDataSource
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return transactionTemplate;
    }
}
