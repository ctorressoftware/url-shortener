package io.github.ctorressoftware.urlshortener.application.service;

import io.github.ctorressoftware.urlshortener.application.ports.in.create.CreateShortUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.create.CreateShortUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.create.CreateShortUrlUseCase;
import io.github.ctorressoftware.urlshortener.application.ports.out.ShortUrlRepository;
import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class CreateShortUrlService implements CreateShortUrlUseCase {

    private final Clock clock;
    private final ShortUrlRepository repository;

    public CreateShortUrlService(
            ShortUrlRepository repository,
            Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    @Override
    public CreateShortUrlResult create(CreateShortUrlCommand command) {

        ShortUrl shortUrl = ShortUrl.create(
                command.originalUrl(),
                command.shortUrl(),
                clock
        );

        ShortUrl savedUrl = repository.save(shortUrl);

        return new CreateShortUrlResult(savedUrl);
    }
}
