FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY build/libs/backmonstrao-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT java -jar app.jar