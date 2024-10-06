# Stage 1: Build the application
FROM maven:3.6.3-jdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the src directory
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/backend-0.0.1.jar backend.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "backend.jar"]
