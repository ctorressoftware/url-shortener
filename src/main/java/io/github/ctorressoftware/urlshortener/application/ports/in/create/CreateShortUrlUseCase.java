package io.github.ctorressoftware.urlshortener.application.ports.in.create;

public interface CreateShortUrlUseCase {
    CreateShortUrlResult create(CreateShortUrlCommand command);
}
