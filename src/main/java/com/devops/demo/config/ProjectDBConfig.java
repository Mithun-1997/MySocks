package com.devops.demo.config;

import com.devops.demo.propertiesReader.ProjectDatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = {
        "com.devops.demo.database.repository.projectrepository.userrepository",
        "com.devops.demo.database.repository.projectrepository.springSession",
        "com.devops.demo.database.repository.projectrepository.springsessionattributesrepository"
    },
    entityManagerFactoryRef = "projectEntityManagerFactory",
    transactionManagerRef = "projectTransactionManager"
)
public class ProjectDBConfig {

    private final ProjectDatasourceProperties properties;

    public ProjectDBConfig(ProjectDatasourceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public DataSource projectDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getJdbcUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setDriverClassName(properties.getDriverClassName());
        config.setMaximumPoolSize(properties.getMaximumPoolSize());
        config.setMinimumIdle(properties.getMinimumIdle());
        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean projectEntityManagerFactory(
            @Qualifier("projectDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", properties.getHibernate().getHbm2ddlAuto());
        props.put("hibernate.dialect", properties.getHibernate().getDialect());

        return builder
                .dataSource(dataSource)
                .packages("com.devops.demo.database.entity.project")
                .persistenceUnit("project")
                .properties(props)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager projectTransactionManager(
            @Qualifier("projectEntityManagerFactory") EntityManagerFactory projectEntityManagerFactory) {
        return new JpaTransactionManager(projectEntityManagerFactory);
    }
}