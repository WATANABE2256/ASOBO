package com.asobo.config;

import com.zaxxer.hikari.HikariDataSource;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class RailwayDatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(RailwayDatabaseConfig.class);

    @Bean
    public DataSource dataSource(
            @Value("${DATABASE_URL:}") String databaseUrl,
            @Value("${DATABASE_PRIVATE_URL:}") String databasePrivateUrl,
            @Value("${PGHOST:}") String pgHost,
            @Value("${PGPORT:5432}") String pgPort,
            @Value("${PGUSER:}") String pgUser,
            @Value("${PGPASSWORD:}") String pgPassword,
            @Value("${PGDATABASE:}") String pgDatabase) {

        String effectiveUrl = firstNonBlank(databaseUrl, databasePrivateUrl);

        if (effectiveUrl != null) {
            log.info("Configuring PostgreSQL from DATABASE_URL");
            return createPostgresDataSource(parseDatabaseUrl(effectiveUrl));
        }

        if (pgHost != null && !pgHost.isBlank()) {
            log.info("Configuring PostgreSQL from PGHOST={}", pgHost);
            ParsedDatabase parsed = new ParsedDatabase(
                    "jdbc:postgresql://" + pgHost + ":" + pgPort + "/" + pgDatabase,
                    pgUser,
                    pgPassword);
            return createPostgresDataSource(parsed);
        }

        log.warn("PostgreSQL not configured. Falling back to in-memory H2 for startup.");
        return createH2DataSource();
    }

    @Bean
    public HibernatePropertiesCustomizer hibernateDialectCustomizer(
            @Value("${DATABASE_URL:}") String databaseUrl,
            @Value("${DATABASE_PRIVATE_URL:}") String databasePrivateUrl,
            @Value("${PGHOST:}") String pgHost) {

        boolean usePostgres = firstNonBlank(databaseUrl, databasePrivateUrl) != null
                || (pgHost != null && !pgHost.isBlank());

        return hibernateProperties -> {
            if (usePostgres) {
                hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            } else {
                hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            }
        };
    }

    private HikariDataSource createPostgresDataSource(ParsedDatabase parsed) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl(parsed.jdbcUrl());
        dataSource.setUsername(parsed.username());
        dataSource.setPassword(parsed.password());
        dataSource.setConnectionTimeout(10_000);
        dataSource.setInitializationFailTimeout(10_000);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }

    private HikariDataSource createH2DataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:mem:asobo;DB_CLOSE_DELAY=-1;MODE=PostgreSQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
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
                    username = URLDecoder.decode(userInfo.substring(0, colonIndex), StandardCharsets.UTF_8);
                    password = URLDecoder.decode(userInfo.substring(colonIndex + 1), StandardCharsets.UTF_8);
                } else {
                    username = URLDecoder.decode(userInfo, StandardCharsets.UTF_8);
                }
            }

            return new ParsedDatabase(jdbcUrl, username, password);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid DATABASE_URL format", e);
        }
    }

    private String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }

    private record ParsedDatabase(String jdbcUrl, String username, String password) {
    }
}
