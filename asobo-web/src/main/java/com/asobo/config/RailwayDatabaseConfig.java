package com.asobo.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class RailwayDatabaseConfig {

    @Bean
    public DataSource dataSource(
            @Value("${DATABASE_URL:}") String databaseUrl,
            @Value("${PGHOST:}") String pgHost,
            @Value("${PGPORT:5432}") String pgPort,
            @Value("${PGUSER:}") String pgUser,
            @Value("${PGPASSWORD:}") String pgPassword,
            @Value("${PGDATABASE:}") String pgDatabase) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        if (databaseUrl != null && !databaseUrl.isBlank()) {
            ParsedDatabase parsed = parseDatabaseUrl(databaseUrl);
            dataSource.setJdbcUrl(parsed.jdbcUrl());
            dataSource.setUsername(parsed.username());
            dataSource.setPassword(parsed.password());
        } else if (pgHost != null && !pgHost.isBlank()) {
            dataSource.setJdbcUrl(
                    "jdbc:postgresql://" + pgHost + ":" + pgPort + "/" + pgDatabase);
            dataSource.setUsername(pgUser);
            dataSource.setPassword(pgPassword);
        } else {
            throw new IllegalStateException(
                    "PostgreSQL is not configured. Link a PostgreSQL service on Railway "
                            + "or set DATABASE_URL / PGHOST environment variables.");
        }

        return dataSource;
    }

    private ParsedDatabase parseDatabaseUrl(String url) {
        try {
            String normalized = url.replace("postgresql://", "postgres://");
            java.net.URI uri = new java.net.URI(normalized);

            String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();

            String username = "";
            String password = "";
            String userInfo = uri.getUserInfo();
            if (userInfo != null) {
                int colonIndex = userInfo.indexOf(':');
                if (colonIndex >= 0) {
                    username = userInfo.substring(0, colonIndex);
                    password = userInfo.substring(colonIndex + 1);
                } else {
                    username = userInfo;
                }
            }

            return new ParsedDatabase(jdbcUrl, username, password);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid DATABASE_URL format: " + url, e);
        }
    }

    private record ParsedDatabase(String jdbcUrl, String username, String password) {
    }
}
