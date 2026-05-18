package io.github.ctorressoftware.urlshortener.application.ports.in.get;

public interface GetByOriginalUrlUseCase {
    GetByOriginalUrlResult getByOriginalUrl(String originalUrl);
}
