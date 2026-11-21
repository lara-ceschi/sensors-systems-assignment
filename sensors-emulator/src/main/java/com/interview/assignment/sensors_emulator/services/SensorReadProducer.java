package com.interview.assignment.sensors_emulator.services;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.assignment.sensors_emulator.sensors.Sensor;
import com.interview.assignment.sensors_emulator.sensors.SensorRead;
import com.rabbitmq.client.amqp.Connection;
import com.rabbitmq.client.amqp.Environment;
import com.rabbitmq.client.amqp.Publisher;
import com.rabbitmq.client.amqp.impl.AmqpEnvironmentBuilder;

@Service 
public class SensorReadProducer {
private static final Logger log = LoggerFactory.getLogger(SensorReadProducer.class);

    private static final String QUEUE_LETTURE = "LETTURE";

    private List<Sensor> sensors = List.of(new Sensor(1,1), new Sensor(1,2), new Sensor(2,1), new Sensor(2,3));


    public void sendValidRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura valida per sensore", sensor);

            SensorRead read = new SensorRead(sensor);
            serializeAndSendRead(read);
        }
    }


    public void sendInvalidHumidityRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per umidità per sensore", sensor);

            SensorRead read = new SensorRead(sensor);
            read.setHumidityPercentage(150);

            serializeAndSendRead(read);
        }
    }


    public void sendInvalidTemperatureRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per temperatura per sensore", sensor);

            SensorRead read = new SensorRead(sensor);
            read.setTemperature(150d);

            serializeAndSendRead(read);
        }
    }


    
    public void sendInvalidLightRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per luce per sensore", sensor);

            SensorRead read = new SensorRead(sensor);
            read.setLight(-1);

            serializeAndSendRead(read);
        }
    }


    
    public void sendInvalidTimestampRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per timestamp per sensore", sensor);

            SensorRead read = new SensorRead(sensor);
            read.setTimestamp(new Timestamp(System.currentTimeMillis() + 10000000));

            serializeAndSendRead(read);
        }
    }


    private void serializeAndSendRead(SensorRead read) {
        try {
            String jsonRead = new ObjectMapper().writeValueAsString(read);

            sendRead(jsonRead);

        } catch (JsonProcessingException e) {
            log.error("Eccezione nella serializzazione della lettura: {}", read, e);
        }
    }


    private void sendRead(String sensorRead) {
            try (Connection connection = getRabbitMQEnvironment().connectionBuilder().build()) {
                //creiamo la coda se non c'è
                connection.management().queue(QUEUE_LETTURE).quorum().queue().declare();
                
                try (Publisher publisher = connection.publisherBuilder().queue(QUEUE_LETTURE).build()) {
                    CountDownLatch latch = new CountDownLatch(1);
                    publisher.publish(
                        publisher.message(sensorRead.getBytes(StandardCharsets.UTF_8)),
                        context -> {
                            if (context.status() == Publisher.Status.ACCEPTED) {
                                log.info("Spedito messaggio: {}",sensorRead);;
                            }
                            latch.countDown();
                        });
                        if (!latch.await(5, TimeUnit.SECONDS)) {
                            throw new IllegalStateException("Timeout nell'attesa della risposta del publisher");
                        }
                } catch (InterruptedException e) {
                    log.error("Eccezione nell'invio della lettura!", e);
                    //TODO: gestire meglio questa eccezione
                }

        }
    }


    // TODO: togliere l'ip statico e usare rabbitmq mettendo i container sotto la stessa subnet
   private Environment getRabbitMQEnvironment() {
        return new AmqpEnvironmentBuilder()
            .connectionSettings()
            .uri("amqp://guest:guest@172.17.0.2:5672/%2f")
            .environmentBuilder()
            .build();
   }
}
