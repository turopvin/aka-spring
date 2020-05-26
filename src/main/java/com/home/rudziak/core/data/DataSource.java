package com.home.rudziak.core.data;

import com.home.rudziak.core.configurator.annotations.PostConstruct;
import com.home.rudziak.core.configurator.annotations.Repository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import java.sql.Connection;

@Repository
public class DataSource {

    private String url;
    private String username;
    private String password;
    private HikariConfig config = new HikariConfig();
    private HikariDataSource dataSource;

    public DataSource() {
    }

    @PostConstruct
    public void init() {
        this.config.setJdbcUrl(url);
        this.config.setUsername(username);
        this.config.setPassword(password);
        dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }
}
