package com.devops.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableJdbcHttpSession
public class SessionDBConfig {

    @Bean(name = "sessionDataSource")
    public DataSource sessionDataSource(@Qualifier("projectDataSource") DataSource projectDataSource) {
        // Use the existing projectDataSource for session management
        return projectDataSource;
    }
}

