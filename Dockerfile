FROM openjdk:21
COPY build/libs/fun-generator-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]