FROM openjdk:8
ADD target/docker-payment-service.jar docker-payment-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "docker-payment-service.jar"]
