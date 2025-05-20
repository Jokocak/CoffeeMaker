# -- Build Steps --
FROM maven:3.9.9-eclipse-temurin-11 AS build

WORKDIR /workspace

COPY . .

RUN mvn clean package -DskipTests

# -- Production Steps --

# Use OpenJDK 11 as the base image. You can use 'slim' for smaller size or 'alpine' for even smaller (and lighter).
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file produced by Maven into the container
COPY --from=build /workspace/target/*.jar app.jar

# Expose port 8080 (or the port your app uses)
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
