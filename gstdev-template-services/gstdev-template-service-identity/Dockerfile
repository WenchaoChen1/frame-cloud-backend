FROM openjdk:17
COPY ./target/gstdev-template-service-identity-1.0.0-SNAPSHOT.jar /api/gstdev-identity.jar
WORKDIR /api
CMD ["java", "-jar", "gstdev-identity.jar"]
