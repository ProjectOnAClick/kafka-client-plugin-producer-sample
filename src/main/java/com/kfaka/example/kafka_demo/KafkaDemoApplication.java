package com.kfaka.example.kafka_demo;

import com.kfaka.example.kafka_demo.pojo.BookingEvent;
import com.wbs.kafka.plugin.client.KafkaPlugin;
import com.wbs.kafka.plugin.exception.KafkaPluginException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class KafkaDemoApplication {

	public static void main(String[] args) throws IOException, KafkaPluginException {
		SpringApplication.run(KafkaDemoApplication.class, args);
		KafkaPlugin.initialize("classpath:/application.yml");
		KafkaPlugin.startConsumers("com.wbs.kafka.plugin");

		BookingEvent demo = new BookingEvent("id-001", "Alice’s Ride", "STANDARD");

		KafkaPlugin.producer()
				.send("BookingRequested", demo.getId(), demo,"JSON")
				.whenComplete((meta, err) -> {
					if (err != null) {
						System.err.println("Publish failed: " + err);
					} else {
						System.out.println("Published to "
								+ meta.topic() + "@" + meta.offset());
					}
				});

	}

}
