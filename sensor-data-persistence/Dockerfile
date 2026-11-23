FROM eclipse-temurin:25-jre

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/sensor-data-persistence-*.jar sensor-data-persistence.jar

ENTRYPOINT ["java", "-jar", "sensor-data-persistence.jar"]