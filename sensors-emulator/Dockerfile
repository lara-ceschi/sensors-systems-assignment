FROM eclipse-temurin:25-jre

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/sensors-emulator-*.jar sensors-emulator.jar

ENTRYPOINT ["java", "-jar", "sensors-emulator.jar"]