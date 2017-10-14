package com.github.alexivchenko.simplewebappwithci.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Alex Ivchenko
 */
@Component
@Slf4j
@Profile("db")
public class DatabaseEnvironmentVariablesLogger {
    @Value("${JDBC_DATABASE_URL}")
    private String databaseUrl;
    @Value("${JDBC_DATABASE_USERNAME}")
    private String username;
    @Value("${JDBC_DATABASE_PASSWORD}")
    private String password;

    @PostConstruct
    public void log() {
        log.info(String.format("Database url = %s", databaseUrl));
        log.info(String.format("Database username = %s", username));
        log.info(String.format("Database password = %s", password));
    }
}
