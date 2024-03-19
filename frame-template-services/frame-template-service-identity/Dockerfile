FROM openjdk:17
COPY ./target/frame-template-service-identity-1.0.0-SNAPSHOT.jar /api/frame-identity.jar
WORKDIR /api
CMD ["java", "-jar", "frame-identity.jar"]
