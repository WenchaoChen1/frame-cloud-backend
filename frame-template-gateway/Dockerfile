FROM openjdk:17
COPY ./target/frame-template-gateway-0.0.1-SNAPSHOT.jar /api/frame-gateway.jar
WORKDIR /api
CMD ["java", "-jar", "frame-gateway.jar"]
