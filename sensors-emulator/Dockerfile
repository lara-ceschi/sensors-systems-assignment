FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/sensors-emulator-*.jar sensors-emulator.jar

ENTRYPOINT ["java", "-jar", "sensors-emulator.jar"]