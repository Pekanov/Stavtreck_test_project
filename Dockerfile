FROM gradle:8.6-jdk17-alpine
WORKDIR /app
COPY build/libs/*-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
