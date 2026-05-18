package io.github.ctorressoftware.urlshortener.infrastructure.randomgenerator;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import io.github.ctorressoftware.urlshortener.application.ports.out.RandomCodeGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NanoIdGenerator implements RandomCodeGenerator {
    private final Random random;
    private final static int SIZE = 6;

    public NanoIdGenerator(Random random) {
        this.random = random;
    }

    @Override
    public String generate() {
        return NanoIdUtils.randomNanoId(
                random,
                NanoIdUtils.DEFAULT_ALPHABET,
                SIZE
        );
    }
}
