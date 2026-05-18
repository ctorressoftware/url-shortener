package io.github.ctorressoftware.urlshortener.application.ports.in.build;

public interface BuildShortenedUrlUseCase {
    BuildShortenedUrlResult build(BuildShortenedUrlCommand command);
}