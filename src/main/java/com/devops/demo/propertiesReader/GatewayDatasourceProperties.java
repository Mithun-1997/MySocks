package com.devops.demo.propertiesReader;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.gateway")
public class GatewayDatasourceProperties {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private int maximumPoolSize;
    private int minimumIdle;
    private String dataSqlPath;
    private Hibernate hibernate = new Hibernate();

    public static class Hibernate {
        private String hbm2ddlAuto;
        private String dialect;

        public String getHbm2ddlAuto() { return hbm2ddlAuto; }
        public void setHbm2ddlAuto(String hbm2ddlAuto) { this.hbm2ddlAuto = hbm2ddlAuto; }
        public String getDialect() { return dialect; }
        public void setDialect(String dialect) { this.dialect = dialect; }
    }

    public String getJdbcUrl() { return jdbcUrl; }
    public void setJdbcUrl(String jdbcUrl) { this.jdbcUrl = jdbcUrl; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDriverClassName() { return driverClassName; }
    public void setDriverClassName(String driverClassName) { this.driverClassName = driverClassName; }
    public int getMaximumPoolSize() { return maximumPoolSize; }
    public void setMaximumPoolSize(int maximumPoolSize) { this.maximumPoolSize = maximumPoolSize; }
    public int getMinimumIdle() { return minimumIdle; }
    public void setMinimumIdle(int minimumIdle) { this.minimumIdle = minimumIdle; }
    public String getDataSqlPath() { return dataSqlPath; }
    public void setDataSqlPath(String dataSqlPath) { this.dataSqlPath = dataSqlPath; }
    public Hibernate getHibernate() { return hibernate; }
    public void setHibernate(Hibernate hibernate) { this.hibernate = hibernate; }
}