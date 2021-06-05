FROM openjdk:8
ADD target/docker-api-gateway.jar docker-api-gateway.jar
EXPOSE 8989
ENTRYPOINT ["java", "-jar", "docker-api-gateway.jar"]