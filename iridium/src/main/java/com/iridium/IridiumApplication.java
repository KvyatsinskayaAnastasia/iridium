package com.iridium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude =
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
)
public final class IridiumApplication {

    private IridiumApplication() {

    }

    /**
     * The main method for application.
     * @param args args
     */
    public static void main(final String[] args) {
        SpringApplication.run(IridiumApplication.class, args);
    }
}
