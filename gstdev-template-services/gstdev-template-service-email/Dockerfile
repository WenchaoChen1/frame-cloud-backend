FROM openjdk:17
COPY ./target/gstdev-template-service-email-1.0.0-SNAPSHOT.jar /api/gstdev-email.jar
WORKDIR /api
CMD ["java", "-jar", "gstdev-email.jar"]
