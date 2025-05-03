package com.mikaz.fifa.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbConnection {
    private static final int port = Integer.parseInt(System.getenv("PORT"));
    private static final String host = System.getenv("HOST");
    private static final String username = System.getenv("USERNAME");
    private static final String password = System.getenv("PASSWORD");
    private static final String database = System.getenv("DATABASE");
    private final String url;

    public DbConnection() {
        url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
    }

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
