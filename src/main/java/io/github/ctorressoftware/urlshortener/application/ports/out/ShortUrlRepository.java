package io.github.ctorressoftware.urlshortener.application.ports.out;

import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;

import java.util.Optional;

public interface ShortUrlRepository {
    ShortUrl save(ShortUrl shortUrl);
    Optional<ShortUrl> findByOriginalUrl(String originalUrl);
    Optional<ShortUrl> findByShortCode(String shortCode);
}
