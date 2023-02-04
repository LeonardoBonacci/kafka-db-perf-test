package guru.bonacci.performance.kafsql;

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
public class KafSqlReactiveApp implements CommandLineRunner {

	private final ReactiveKafkaConsumerTemplate<String, String> kTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(KafSqlReactiveApp.class, args);
	}
	
	
	private final FooRepo repo;
	
	private Flux<Foo> consume() {
   return kTemplate
           .receiveAutoAck()
           .map(record -> {
          	 long now = System.currentTimeMillis();
          	 var message = record.value();
             log.debug("Received Message: {} at {}", message, now);
             return Foo.builder().bar(message).whenn(now).build();
           })
           .flatMap(repo::save);
	}
	
	@Override
  public void run(String... args) {
      consume().subscribe();
  }
}
