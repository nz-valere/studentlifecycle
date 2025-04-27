# For StudentLifecycle
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/StudentLifecylce-0.0.1-SNAPSHOT.jar .
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "StudentLifecylce-0.0.1-SNAPSHOT.jar"]
