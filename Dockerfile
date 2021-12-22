FROM openjdk:8-jdk-alpine
COPY api/target/api-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]