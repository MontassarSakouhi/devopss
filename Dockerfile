FROM openjdk:17-jdk-alpine
COPY target/myapp.jar /app/myapp.jar
WORKDIR /app
CMD ["java", "-jar", "myapp.jar"]