package io.github.ctorressoftware.urlshortener.infrastructure.web;

import io.github.ctorressoftware.urlshortener.application.ports.in.build.BuildShortenedUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.build.BuildShortenedUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.build.BuildShortenedUrlUseCase;
import io.github.ctorressoftware.urlshortener.infrastructure.web.request.BuildShortenedUrlRequest;
import io.github.ctorressoftware.urlshortener.infrastructure.web.response.BuildShortenedUrlResponse;
import io.github.ctorressoftware.urlshortener.infrastructure.web.shared.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/short-urls")
public class ShortenUrlController {

    private final BuildShortenedUrlUseCase buildShortenedUrlService;

    public ShortenUrlController(BuildShortenedUrlUseCase buildShortenedUrlService) {
        this.buildShortenedUrlService = buildShortenedUrlService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BuildShortenedUrlResponse>> build(
            @RequestBody @Valid BuildShortenedUrlRequest request) {

        BuildShortenedUrlCommand command = new BuildShortenedUrlCommand(
                request.originalUrl()
        );

        BuildShortenedUrlResult result = buildShortenedUrlService.build(command);

        return ResponseEntity.ok(ApiResponse.success(
                new BuildShortenedUrlResponse(result.shortenedUrl())
        ));
    }
}
