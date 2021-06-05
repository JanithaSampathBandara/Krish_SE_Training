FROM openjdk:8
ADD target/docker-email-service.jar docker-email-service.jar
EXPOSE 5656
ENTRYPOINT ["java", "-jar", "docker-email-service.jar"]