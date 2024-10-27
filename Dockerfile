FROM maven:3.6.3-jdk-11 AS build
COPY . /app
WORKDIR /app
RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /app/target/backend-0.0.1.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]
