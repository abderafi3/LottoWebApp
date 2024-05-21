FROM openjdk:17-jdk-slim

ENV JAVA_HOME /usr/local/openjdk-17

WORKDIR /app

COPY target/Lotto_Web_App-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
