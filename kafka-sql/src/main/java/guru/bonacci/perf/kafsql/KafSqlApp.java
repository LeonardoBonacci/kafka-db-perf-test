
package guru.bonacci.perf.kafsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class KafSqlApp {

	public static void main(String[] args) {
		SpringApplication.run(KafSqlApp.class, args);
	}
	
	
	private final FooRepo repo;
	
	@KafkaListener(topics = "topic-perf", groupId = "i-am-blocking")
	public void listen(@Payload String message) {
		long now = System.currentTimeMillis();
    log.debug("Received Message: {} at {}", message, now);
    repo.save(Foo.builder().bar(message).whenn(now).build());
	}
}
