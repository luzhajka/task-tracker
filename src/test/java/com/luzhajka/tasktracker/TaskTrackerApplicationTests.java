package com.luzhajka.tasktracker;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.spock.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {TaskTrackerApplicationTests.Initializer.class})
class TaskTrackerApplicationTests {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("12345");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

//    @Test //IllegalStateException: Could not find a valid Docker environment.
    void contextLoads() {
        assertTrue(postgreSQLContainer.isRunning());
    }

}
