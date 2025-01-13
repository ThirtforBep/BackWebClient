# Use the official OpenJDK image from Docker Hub
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the local machine to the container
COPY target/proyectoWebClient.jar /app/proyectoWebClient.jar

# Expose the port on which your app runs
EXPOSE 8080

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "proyectoWebClient.jar"]
