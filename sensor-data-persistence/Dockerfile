FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/sensor-data-persistence-*.jar sensor-data-persistence.jar

ENTRYPOINT ["java", "-jar", "sensor-data-persistence.jar"]