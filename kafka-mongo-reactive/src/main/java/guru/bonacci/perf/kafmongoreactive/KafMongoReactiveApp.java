package guru.bonacci.perf.kafmongoreactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication 
@RequiredArgsConstructor
public class KafMongoReactiveApp implements CommandLineRunner {

	private final ReactiveKafkaConsumerTemplate<String, String> kTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(KafMongoReactiveApp.class, args);
	}
	
	
	private final FooRepo repo;
	private long counter = 0l;
	
	private Flux<Foo> consume() {
   return kTemplate
           .receiveAutoAck()
           .map(record -> {
          	 long now = System.currentTimeMillis();
          	 var message = record.value();
             log.debug("Received Message: {} at {}", message, now);
             return Foo.builder().id(counter++).bar(message).whenn(now).build();
           })
           .flatMap(repo::save);
	}
	
	@Override
  public void run(String... args) {
      consume().subscribe();
  }
}
