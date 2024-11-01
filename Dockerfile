FROM maven:3.6.3-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/backend-0.0.1.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java" ,"-jar","backend.jar"]
