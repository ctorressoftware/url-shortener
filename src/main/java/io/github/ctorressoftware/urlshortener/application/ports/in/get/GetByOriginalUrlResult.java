package io.github.ctorressoftware.urlshortener.application.ports.in.get;

import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;

import java.util.Optional;

public record GetByOriginalUrlResult(Optional<ShortUrl> optionalShortUrl) {}
