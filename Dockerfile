# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY gradlew .
COPY gradle ./gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon
COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-ubi10-minimal
WORKDIR /app
RUN useradd -r -u 1001 appuser
COPY --from=builder /app/build/libs/*.jar app.jar
RUN chown appuser:appuser app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]