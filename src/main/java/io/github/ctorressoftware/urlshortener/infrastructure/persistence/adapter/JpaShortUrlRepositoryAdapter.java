package io.github.ctorressoftware.urlshortener.infrastructure.persistence.adapter;

import io.github.ctorressoftware.urlshortener.application.ports.out.ShortUrlRepository;
import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;
import io.github.ctorressoftware.urlshortener.infrastructure.persistence.entity.ShortUrlEntity;
import io.github.ctorressoftware.urlshortener.infrastructure.persistence.repository.SpringDataShortUrlRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaShortUrlRepositoryAdapter implements ShortUrlRepository {

    private final SpringDataShortUrlRepository repository;

    public JpaShortUrlRepositoryAdapter(SpringDataShortUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShortUrl save(ShortUrl shortUrl) {
        ShortUrlEntity saved = repository.save(toEntity(shortUrl));
        return toDomain(saved);
    }

    @Override
    public Optional<ShortUrl> findByOriginalUrl(String originalUrl) {
        Optional<ShortUrlEntity> url = repository.findByOriginalUrl(originalUrl);
        return url.map(this::toDomain);
    }

    private ShortUrlEntity toEntity(ShortUrl shortUrl) {
        ShortUrlEntity entity = new ShortUrlEntity();
        entity.setId(shortUrl.getId());
        entity.setOriginalUrl(shortUrl.getOriginalUrl());
        entity.setShortCode(shortUrl.getShortCode());
        entity.setActive(shortUrl.isActive());
        entity.setCreatedAt(shortUrl.getCreatedAt());
        entity.setUpdatedAt(shortUrl.getUpdatedAt());
        entity.setExpiresAt(shortUrl.getExpiresAt());
        return entity;
    }

    private ShortUrl toDomain(ShortUrlEntity entity) {
        return ShortUrl.restore(
                entity.getId(),
                entity.getOriginalUrl(),
                entity.getShortCode(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getExpiresAt()
        );
    }
}
