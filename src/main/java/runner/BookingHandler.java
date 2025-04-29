package runner;


import com.kfaka.example.kafka_demo.pojo.BookingEvent;
import com.wbs.kafka.plugin.annotation.KafkaHandler;
import com.wbs.kafka.plugin.annotation.SerializationFormat;

public class BookingHandler {

    @KafkaHandler(
            topic = "BookingRequested",
            groupId = "booking-group",
            format = SerializationFormat.JSON,
            retries = 5,
            dlqTopic = "BookingRequested.dlq"
    )
    public void onBookingRequested(BookingEvent event) {
        System.out.println("[Handler] Received booking event ▶ " + event);
    }
}
