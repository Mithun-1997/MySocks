package com.devops.demo.config;

import com.devops.demo.propertiesReader.GatewayDatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {
        "com.devops.demo.database.repository.gatewayRepository.endpointpermissionsrepository",
        "com.devops.demo.database.repository.gatewayRepository.filtersrepository",
        "com.devops.demo.database.repository.gatewayRepository.serverurlrepository",
        "com.devops.demo.database.repository.gatewayRepository.serviceurlrepository"
    },
    entityManagerFactoryRef = "gatewayEntityManagerFactory",
    transactionManagerRef = "gatewayTransactionManager"
)
public class GatewayDBConfig {

    private static final Logger logger = LoggerFactory.getLogger(GatewayDBConfig.class);

    private final GatewayDatasourceProperties properties;
    private final Environment environment;

    public GatewayDBConfig(GatewayDatasourceProperties properties, Environment environment) {
        this.properties = properties;
        this.environment = environment;
    }

    @PostConstruct
    public void printLoadedProperties() {
        System.out.println("======== ✅ Gateway Datasource Properties Loaded ========");
        System.out.println("JDBC URL           : " + properties.getJdbcUrl());
        System.out.println("Username           : " + properties.getUsername());
        System.out.println("Hibernate DDL Auto : " + properties.getHibernate().getHbm2ddlAuto());
        System.out.println("Hibernate Dialect  : " + properties.getHibernate().getDialect());
        System.out.println("Data SQL Path      : " + resolveDataSqlPath());
        System.out.println("=========================================================");
    }

    @Bean(name = "gatewayDataSource")
    public DataSource gatewayDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getJdbcUrl());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setDriverClassName(properties.getDriverClassName());
        config.setMaximumPoolSize(properties.getMaximumPoolSize());
        config.setMinimumIdle(properties.getMinimumIdle());
        return new HikariDataSource(config);
    }

    @Bean(name = "gatewayEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean gatewayEntityManagerFactory(
            @Qualifier("gatewayDataSource") DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        HashMap<String, Object> jpaProps = new HashMap<>();
        jpaProps.put("hibernate.hbm2ddl.auto", properties.getHibernate().getHbm2ddlAuto());
        jpaProps.put("hibernate.dialect", properties.getHibernate().getDialect());

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.devops.demo.database.entity.gateway");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(jpaProps);
        factory.setPersistenceUnitName("gateway");
        return factory;
    }

    @Bean(name = "gatewayTransactionManager")
    public PlatformTransactionManager gatewayTransactionManager(
            @Qualifier("gatewayEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
        return new JpaTransactionManager(factory.getObject());
    }

    @Bean
    public DataSourceInitializer gatewayDataSourceInitializer(
            @Qualifier("gatewayDataSource") DataSource dataSource,
            @Qualifier("gatewayEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        // Ensure tables are created by Hibernate first
        entityManagerFactory.afterPropertiesSet();

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        String dataSqlPath = resolveDataSqlPath();
        logger.info("Resolved Data SQL Path: {}", dataSqlPath);

        if (dataSqlPath != null) {
            File scriptFile = new File(dataSqlPath);
            if (!scriptFile.exists()) {
                logger.error("SQL script file does not exist at: {}", dataSqlPath);
            } else if (!scriptFile.canRead()) {
                logger.error("SQL script file is not readable at: {}", dataSqlPath);
            } else {
                // Check if data exists in a key table
                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                int rowCount;
                try {
                    rowCount = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM endpoint_permissions", Integer.class);
                } catch (Exception e) {
                    logger.warn("Error checking data in endpoint_permissions, assuming no data: {}", e.getMessage());
                    rowCount = 0; // Assume no data if table doesn’t exist yet or query fails
                }

                if (rowCount == 0) {
                    logger.info("No data found in endpoint_permissions, executing SQL script: {}", dataSqlPath);
                    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
                    populator.addScript(new FileSystemResource(dataSqlPath));
                    populator.setContinueOnError(true);
                    populator.setIgnoreFailedDrops(true);
                    initializer.setDatabasePopulator(populator);
                } else {
                    logger.info("Data already exists in gateway schema, skipping SQL script execution.");
                }
            }
        } else {
            logger.warn("No Data SQL Path specified, skipping script execution.");
        }

        return initializer;
    }

    private String resolveDataSqlPath() {
        String rawPath = properties.getDataSqlPath();
        if (rawPath == null) {
            return null;
        }
        return environment.resolvePlaceholders(rawPath);
    }
}