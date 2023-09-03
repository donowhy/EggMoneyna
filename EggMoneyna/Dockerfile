FROM openjdk:11-jre
ARG JAR_FILE=builds/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]