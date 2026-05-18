package io.github.ctorressoftware.urlshortener.application.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import io.github.ctorressoftware.urlshortener.application.ports.in.build.BuildShortenedUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.build.BuildShortenedUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.build.BuildShortenedUrlUseCase;
import io.github.ctorressoftware.urlshortener.application.ports.in.create.CreateShortUrlCommand;
import io.github.ctorressoftware.urlshortener.application.ports.in.create.CreateShortUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.create.CreateShortUrlUseCase;
import io.github.ctorressoftware.urlshortener.application.ports.in.get.GetByOriginalUrlResult;
import io.github.ctorressoftware.urlshortener.application.ports.in.get.GetByOriginalUrlUseCase;
import io.github.ctorressoftware.urlshortener.application.ports.out.RandomCodeGenerator;
import io.github.ctorressoftware.urlshortener.domain.model.ShortUrl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildShortenedUrlService
        implements BuildShortenedUrlUseCase {
    private final CreateShortUrlUseCase createUseCase;
    private final GetByOriginalUrlUseCase getUseCase;
    private final RandomCodeGenerator codeGenerator;
    private static final String BASE_URL = "http://localhost:8080/r/";

    public BuildShortenedUrlService(
            CreateShortUrlUseCase createUseCase,
            GetByOriginalUrlUseCase getUseCase,
            RandomCodeGenerator codeGenerator) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public BuildShortenedUrlResult build(BuildShortenedUrlCommand command) {

        GetByOriginalUrlResult getResult =
                getUseCase.getByOriginalUrl(command.originalUrl());

        Optional<ShortUrl> url = getResult.optionalShortUrl();

        if (url.isPresent()) {
            String shortenedUrl = BASE_URL + url.get().getShortCode();
            return new BuildShortenedUrlResult(shortenedUrl);
        }

        String shortCode = codeGenerator.generate();
        CreateShortUrlCommand createCommand = new CreateShortUrlCommand(
                command.originalUrl(),
                shortCode
        );

        CreateShortUrlResult saved = createUseCase.create(createCommand);
        ShortUrl shortUrl = saved.shortUrl();
        String shortenedUrl = BASE_URL + shortUrl.getShortCode();

        return new BuildShortenedUrlResult(shortenedUrl);
    }
}
