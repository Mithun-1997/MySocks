package com.devops.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import com.devops.demo.propertiesReader.GatewayDatasourceProperties;
import com.devops.demo.propertiesReader.ProjectDatasourceProperties;

import java.util.Map;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
@EntityScan(basePackages = {
    "com.devops.demo.database.entity.project",
    "com.devops.demo.database.entity.gateway"
})
@EnableConfigurationProperties({
    ProjectDatasourceProperties.class,
    GatewayDatasourceProperties.class
})
@PropertySource("file:${MYSOCKS_HOME}/config/spring.properties")
public class MySocksApplication {

    private static final Logger logger = LoggerFactory.getLogger(MySocksApplication.class);

    public static void main(String[] args) {
        logger.info("Starting MySocksApplication...");

        SpringApplication app = new SpringApplication(MySocksApplication.class);

        String configPath = System.getenv("MYSOCKS_HOME");
        if (configPath == null || configPath.isBlank()) {
            configPath = "D:/devops/mysocks";
            logger.warn("MYSOCKS_HOME not set. Using default: {}", configPath);
        }

        app.setDefaultProperties(Map.of(
            "spring.config.additional-location",
            String.join(",",
                "file:" + configPath + "/config/application.properties",
                "file:" + configPath + "/config/environment.properties"
            )
        ));

        app.run(args);
        logger.info("MySocksApplication started successfully.");
    }
}