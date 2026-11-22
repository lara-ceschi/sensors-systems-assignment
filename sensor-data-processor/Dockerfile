FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/sensor-data-processor-*.jar sensor-data-processor.jar

ENTRYPOINT ["java", "-jar", "sensor-data-processor.jar"]