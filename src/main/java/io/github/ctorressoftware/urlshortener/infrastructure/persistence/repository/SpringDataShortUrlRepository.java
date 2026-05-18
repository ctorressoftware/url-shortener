package io.github.ctorressoftware.urlshortener.infrastructure.persistence.repository;

import io.github.ctorressoftware.urlshortener.infrastructure.persistence.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataShortUrlRepository
        extends JpaRepository<ShortUrlEntity, UUID> {
    Optional<ShortUrlEntity> findByOriginalUrl(String originalUrl);
    Optional<ShortUrlEntity> findByShortCode(String shortCode);
}
