
package guru.bonacci.perf.kafcasbatch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import guru.bonacci.perf.avro.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class KafCasBatchApp {

	public static void main(String[] args) {
		SpringApplication.run(KafCasBatchApp.class, args);
	}
	
	
	private final CFooRepo repo;
	
	@KafkaListener(topics = "perf_avro", groupId = "i-am-blocking-batch-to-cas")
	public void listen(List<Foo> messages) { // default size is 5000
    var foos = messages.stream().map(message -> {
      long now = System.currentTimeMillis();
      log.debug("Received Message: {} at {}", message, now);
      return new CFoo(new CFooKey(message.getBar(), now));

    }).collect(Collectors.toList());
    	
    repo.saveAll(foos);
	}
}
