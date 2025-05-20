# Use OpenJDK 11 as the base image. You can use 'slim' for smaller size or 'alpine' for even smaller (and lighter).
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file produced by Maven into the container
COPY target/CoffeeMaker-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (or the port your app uses)
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]