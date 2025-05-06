package com.kfaka.example.kafka_demo;

import com.kfaka.example.kafka_demo.pojo.BookingEvent;
import com.wbs.kafka.plugin.annotation.SerializationFormat;
import com.wbs.kafka.plugin.client.KafkaPlugin;
import com.wbs.kafka.plugin.exception.KafkaPluginException;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class KafkaDemoApplication {

	public static void main(String[] args) throws IOException, KafkaPluginException, ExecutionException, InterruptedException {
		SpringApplication.run(KafkaDemoApplication.class, args);
		KafkaPlugin.initialize("/application.yml", SerializationFormat.JSON,SerializationFormat.JSON);
		KafkaPlugin.startConsumers("com.wbs.kafka.plugin");

		BookingEvent demo = new BookingEvent("id-001", "Alice’s Ride", "STANDARD");

		RecordMetadata meta = KafkaPlugin.producer()
				.send("BookingRequested", demo.getId(), demo,"JSON")
				.get();

		System.out.println("Published to "
				+ meta.topic() + "@" + meta.offset());

	}

}
