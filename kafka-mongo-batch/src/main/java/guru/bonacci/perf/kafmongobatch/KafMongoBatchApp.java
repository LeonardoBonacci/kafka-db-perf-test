
package guru.bonacci.perf.kafmongobatch;

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
public class KafMongoBatchApp {

	public static void main(String[] args) {
		SpringApplication.run(KafMongoBatchApp.class, args);
	}
	
	
	private final FooRepo repo;
	private long counter = 0l; //cheat
	
	@KafkaListener(topics = "topic-perf", groupId = "i-am-mongo-blocking-batch")
	public void listen(List<String> messages) { // default size is 5000
    var foos = messages.stream().map(message -> {
      long now = System.currentTimeMillis();
      log.debug("Received Message: {} at {}", message, now);
      return Foo.builder().id(counter++).bar(message).whenn(now).build();

    }).collect(Collectors.toList());
    	
    repo.saveAll(foos);
	}
}
