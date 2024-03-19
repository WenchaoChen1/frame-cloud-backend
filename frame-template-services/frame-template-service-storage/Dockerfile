FROM openjdk:17
COPY ./target/frame-template-service-storage-1.0.0-SNAPSHOT.jar /api/frame-storage.jar
WORKDIR /api
CMD ["java", "-jar", "frame-storage.jar"]
