FROM openjdk:17
COPY ./target/frame-template-service-email-1.0.0-SNAPSHOT.jar /api/frame-email.jar
WORKDIR /api
CMD ["java", "-jar", "frame-email.jar"]
