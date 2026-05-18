package io.github.ctorressoftware.urlshortener.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfigurer {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }
}
