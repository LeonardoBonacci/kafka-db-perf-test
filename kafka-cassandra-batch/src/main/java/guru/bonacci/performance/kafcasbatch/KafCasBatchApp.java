
package guru.bonacci.performance.kafcasbatch;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class KafCasBatchApp {

	public static void main(String[] args) {
		SpringApplication.run(KafCasBatchApp.class, args);
	}
	
	@KafkaListener(topics = "topic-perf", groupId = "i-am-blocking-batch")
	public void listen(List<String> messages) { // default size is 5000
    messages.forEach(message -> log.info("Received Message: {} at {}", message, System.currentTimeMillis()));
	}
}
