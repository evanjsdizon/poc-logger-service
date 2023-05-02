FROM eclipse-temurin:17.0.6_10-jre-alpine
WORKDIR /tmp
COPY target/poc-logger-service-0.0.1-SNAPSHOT.jar poc-logger-service.jar
EXPOSE 8020
CMD [ "java", "-jar",  "poc-logger-service.jar"]