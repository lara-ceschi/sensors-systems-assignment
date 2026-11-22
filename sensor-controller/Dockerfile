FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/sensor-controller-*.jar sensor-controller.jar

ENTRYPOINT ["java", "-jar", "sensor-controller.jar"]