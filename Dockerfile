# Stage 1: Build
FROM amazoncorretto:21 AS builder

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
FROM amazoncorretto:21

# Set the working directory for the final image
WORKDIR /app

# Install PostgreSQL client tools to get pg_isready
RUN yum update -y && yum install -y postgresql

# Copy the jar from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Copy the wait-for-postgres script
#COPY wait-for-postgres.sh /wait-for-postgres.sh
#RUN chmod +x /wait-for-postgres.sh

# Expose the port that Spring Boot runs on (default: 8080)
EXPOSE 8080

# Run the script to wait for Postgres and then start the application
ENTRYPOINT ["/bin/sh", "-c", "java -jar app.jar"]
