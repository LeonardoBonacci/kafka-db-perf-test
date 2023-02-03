
package guru.bonacci.performance.kafcas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class KafCasApp {

	public static void main(String[] args) {
		SpringApplication.run(KafCasApp.class, args);
	}
	
	@KafkaListener(topics = "topic-perf", groupId = "i-am-blocking")
	public void listen(@Payload String message) {
    log.info("Received Message: {} at {}", message, System.currentTimeMillis());
	}
}
