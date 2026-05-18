package io.github.ctorressoftware.urlshortener.application.ports.in.redirect;

import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;

public record RedirectOriginalUrlResult(ShortUrl shortUrl) {}
