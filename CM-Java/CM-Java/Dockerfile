# Stage 1: Build with Maven
FROM --platform=linux/amd64 maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /src

# Copy pom.xml and source code
COPY pom.xml /src/
COPY src /src/src

# Build jar
RUN mvn clean package

# Stage 2: Runtime image
FROM --platform=linux/amd64 eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the shaded jar from the build stage
COPY --from=build /src/target/compute-module-1.0.0.jar ./compute-module.jar

# Use non-root user if needed, or keep default
USER 5000

ENTRYPOINT ["java", "-jar", "compute-module.jar"]
