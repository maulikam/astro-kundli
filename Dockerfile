# Stage 1: Build
FROM ghcr.io/graalvm/graalvm-community:21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files (if using Gradle wrapper)
COPY gradlew .
COPY gradle gradle

# Copy the build files and project source code
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Install dependencies and build the application
RUN ./gradlew bootJar

# Stage 2: Run
FROM eclipse-temurin:17-jre

# Set the working directory for the final image
WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port that Spring Boot runs on (default: 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
