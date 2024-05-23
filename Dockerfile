FROM maven:3.8.1-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder ./target/Lotto_Web_App*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


