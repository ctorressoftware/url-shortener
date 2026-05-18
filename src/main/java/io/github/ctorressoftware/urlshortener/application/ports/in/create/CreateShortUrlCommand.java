package io.github.ctorressoftware.urlshortener.application.ports.in.create;

public record CreateShortUrlCommand(
        String originalUrl,
        String shortUrl
) {}