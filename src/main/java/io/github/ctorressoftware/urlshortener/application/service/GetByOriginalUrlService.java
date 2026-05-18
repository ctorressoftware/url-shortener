package io.github.ctorressoftware.urlshortener.application.service;

import io.github.ctorressoftware.urlshortener.application.ports.in.get.GetByOriginalUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.get.GetByOriginalUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.get.GetByOriginalUrlUseCase;
import io.github.ctorressoftware.urlshortener.application.ports.out.ShortUrlRepository;
import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetByOriginalUrlService implements GetByOriginalUrlUseCase {

    private final ShortUrlRepository repository;

    public GetByOriginalUrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetByOriginalUrlResult getByOriginalUrl(String originalUrl) {
        Optional<ShortUrl> result = repository.findByOriginalUrl(originalUrl);
        return new GetByOriginalUrlResult(result);
    }
}
