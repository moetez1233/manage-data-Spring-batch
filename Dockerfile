# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.4-openjdk-17-slim
ADD target/data_excel.jar data_excel.jar

# Expose the application port
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "data_excel.jar"]
