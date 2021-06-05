FROM openjdk:8
ADD target/docker-offence-service.jar docker-offence-service.jar
EXPOSE 8191
ENTRYPOINT ["java", "-jar", "docker-offence-service.jar"]
