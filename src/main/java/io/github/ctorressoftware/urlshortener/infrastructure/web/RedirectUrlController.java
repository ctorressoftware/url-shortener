package io.github.ctorressoftware.urlshortener.infrastructure.web;

import io.github.ctorressoftware.urlshortener.application.ports.in.redirect.RedirectOriginalUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.redirect.RedirectOriginalUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.redirect.RedirectOriginalUrlUseCase;
import io.github.ctorressoftware.urlshortener.infrastructure.web.shared.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/r")
public class RedirectUrlController {

    private final RedirectOriginalUrlUseCase redirectUseCase;

    public RedirectUrlController(RedirectOriginalUrlUseCase redirectUseCase) {
        this.redirectUseCase = redirectUseCase;
    }

    @GetMapping(value = "/{shortCode}")
    public ResponseEntity<ApiResponse<Void>> redirect(
            @PathVariable @Valid @NotBlank(message = "shortCode cannot be blank") String shortCode) {

        RedirectOriginalUrlCommand command = new RedirectOriginalUrlCommand(shortCode);
        RedirectOriginalUrlResult result = redirectUseCase.redirectTo(command);
        URI uri = URI.create(result.shortUrl().getOriginalUrl()).normalize();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(uri)
                .build();
    }
}
