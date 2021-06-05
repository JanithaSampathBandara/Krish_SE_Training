FROM openjdk:8
ADD target/docker-officer-service.jar docker-officer-service.jar
EXPOSE 8193
ENTRYPOINT ["java", "-jar", "docker-officer-service.jar"]
