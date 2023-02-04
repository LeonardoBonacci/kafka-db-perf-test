
package guru.bonacci.performance.kafcasbatch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class KafCasBatchApp {

	public static void main(String[] args) {
		SpringApplication.run(KafCasBatchApp.class, args);
	}
	
	
	private final FooRepo repo;
	
	@KafkaListener(topics = "topic-perf", groupId = "i-am-blocking-batch")
	public void listen(List<String> messages) { // default size is 5000
    var foos = messages.stream().map(message -> {
      long now = System.currentTimeMillis();
      log.debug("Received Message: {} at {}", message, now);
      return new Foo(new FooKey(message, now));

    }).collect(Collectors.toList());
    	
    repo.saveAll(foos);
	}
}
