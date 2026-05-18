package io.github.ctorressoftware.urlshortener.application.service;

import io.github.ctorressoftware.urlshortener.application.ports.in.redirect.RedirectOriginalUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.redirect.RedirectOriginalUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.redirect.RedirectOriginalUrlUseCase;
import io.github.ctorressoftware.urlshortener.application.ports.out.ShortUrlRepository;
import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;
import org.springframework.stereotype.Service;

@Service
public class RedirectOriginalUrlService implements RedirectOriginalUrlUseCase {

    private final ShortUrlRepository repository;

    public RedirectOriginalUrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public RedirectOriginalUrlResult redirectTo(RedirectOriginalUrlCommand command) {

        ShortUrl url = repository.findByShortCode(command.shortCode())
                .orElseThrow(() -> new RuntimeException(
                        "url doesn't exist with code: " + command.shortCode()
                        // create custom exception
                ));

        return new RedirectOriginalUrlResult(url);
    }
}
