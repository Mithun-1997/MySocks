package com.devops.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;


@Configuration
@EnableJdbcHttpSession
public class HttpSessionConfig {
}
