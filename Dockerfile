# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build

ENV TZ=Europe/Berlin

RUN apt-get update && apt-get install -y tzdata

RUN ln -snf /usr/share/zoneinfo/ $TZ /etc/localtime && echo $TZ > /etc/timezone

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Package the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the first stage
COPY --from=build /app/target/Lotto_Web_App-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]



