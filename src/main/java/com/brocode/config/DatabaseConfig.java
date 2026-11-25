package com.brocode.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Konfigurasi JDBC. Default cocok untuk Laragon (user root, tanpa password). */
public final class DatabaseConfig {
    private static final String URL = System.getenv().getOrDefault(
        "BROCODE_DB_URL", "jdbc:mysql://localhost:3306/brocode_queue?serverTimezone=Asia/Jakarta&useSSL=false&allowPublicKeyRetrieval=true");
    private static final String USER = System.getenv().getOrDefault("BROCODE_DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("BROCODE_DB_PASSWORD", "");

    private DatabaseConfig() {}
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
