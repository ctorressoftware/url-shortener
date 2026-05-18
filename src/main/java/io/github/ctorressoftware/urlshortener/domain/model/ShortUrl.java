package io.github.ctorressoftware.urlshortener.domain.model;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class ShortUrl {
    private final UUID id;
    private final String originalUrl;
    private final String shortCode;
    private final boolean active;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final Instant expiresAt;

    private ShortUrl(
            UUID id,
            String originalUrl,
            String shortCode,
            boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant expiresAt) {
        this.id = id;
        this.originalUrl = Objects.requireNonNull(originalUrl, "originalUrl cannot be null");
        this.shortCode = Objects.requireNonNull(shortCode, "shortCode cannot be null");
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt cannot be null");
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
    }

    public static ShortUrl create(
            String originalUrl,
            String shortCode,
            Clock clock) {
        Instant now = Instant.now(clock);
        return new ShortUrl(
                UUID.randomUUID(),
                originalUrl,
                shortCode,
                true,
                now,
                null,
                null
        );
    }

    public static ShortUrl restore(
            UUID id,
            String originalUrl,
            String shortCode,
            boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant expiresAt) {

        return new ShortUrl(
                id,
                originalUrl,
                shortCode,
                active,
                createdAt,
                updatedAt,
                expiresAt
        );
    }

    public UUID getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }
}
