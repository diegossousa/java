FROM openjdk:11-jre-slim

EXPOSE 8080

VOLUME /dio

WORKDIR /app

ADD /target/personapi-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]