
package guru.bonacci.perf.kafcas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import guru.bonacci.perf.avro.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class KafCasApp {

	public static void main(String[] args) {
		SpringApplication.run(KafCasApp.class, args);
	}
	
	
	private final CFooRepo repo;
	
	@KafkaListener(topics = "perf_avro", groupId = "i-am-blocking-to-cas")
	public void listen(@Payload Foo message) {
		long now = System.currentTimeMillis();
    log.debug("Received Message: {} at {}", message, now);
    repo.save(new CFoo(new CFooKey(message.getBar(), now)));
	}
}
