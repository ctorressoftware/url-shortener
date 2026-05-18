package io.github.ctorressoftware.urlshortener.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.random.RandomGenerator;

@Configuration
public class RandomConfigurer {
    @Bean
    Random random() {
        return Random.from(RandomGenerator.getDefault());
    }
}
