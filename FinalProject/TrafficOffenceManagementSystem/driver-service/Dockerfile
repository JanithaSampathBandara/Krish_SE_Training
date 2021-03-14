FROM openjdk:8
ADD target/docker-driver-service.jar docker-driver-service.jar
EXPOSE 8192
ENTRYPOINT ["java", "-jar", "docker-driver-service.jar"]

