FROM openjdk:8
ADD target/docker-oauth-server.jar docker-oauth-server.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "docker-oauth-server.jar"]