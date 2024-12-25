FROM gradle:8.6-jdk17-alpine
WORKDIR /app
# Копируем собранный JAR
COPY build/libs/*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]