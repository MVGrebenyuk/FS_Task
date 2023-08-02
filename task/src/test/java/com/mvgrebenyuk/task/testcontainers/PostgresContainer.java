package com.mvgrebenyuk.task.testcontainers;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
            .withReuse(true)
            .withDatabaseName("postgres")
            .withInitScript("init.sql");

    static {
        postgreSQLContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "FSTT_POSTGRES_USER=" + postgreSQLContainer.getUsername(),
                "FSTT_POSTGRES_PASS=" + postgreSQLContainer.getPassword(),
                "FSTT_POSTGRES_URL=" + postgreSQLContainer.getJdbcUrl()
        ).applyTo(applicationContext.getEnvironment());
    }

}
