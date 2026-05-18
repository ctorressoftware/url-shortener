package io.github.ctorressoftware.urlshortener.application.ports.in.redirect;

public interface RedirectOriginalUrlUseCase {
    RedirectOriginalUrlResult redirectTo(RedirectOriginalUrlCommand command);
}
