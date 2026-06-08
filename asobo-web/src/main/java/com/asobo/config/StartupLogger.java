package com.asobo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    private static final Logger log = LoggerFactory.getLogger(StartupLogger.class);

    private final Environment environment;

    public StartupLogger(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        String port = environment.getProperty("local.server.port", environment.getProperty("server.port", "unknown"));
        String[] profiles = environment.getActiveProfiles();
        log.info("ASOBO started on port {} with profiles: {}", port, String.join(", ", profiles));
    }
}
