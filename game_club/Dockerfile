# Use an official OpenJDK image for Java 21
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the built jar from target folder
COPY target/game_club-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]