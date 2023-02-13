
package guru.bonacci.perf.kafcasreact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import guru.bonacci.perf.avro.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication 
@RequiredArgsConstructor
public class KafCasReactiveApp implements CommandLineRunner {

	private final ReactiveKafkaConsumerTemplate<String, Foo> kTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(KafCasReactiveApp.class, args);
	}
	
	
	private final CFooRepo repo;
	
	private Flux<CFoo> consume() {
   return kTemplate
           .receiveAutoAck()
           .map(record -> {
          	 long now = System.currentTimeMillis();
          	 var message = record.value();
             log.debug("Received Message: {} at {}", message, now);
             return new CFoo(new CFooKey(message.getBar(), now));
           })
           .flatMap(repo::save);
	}
	
	@Override
  public void run(String... args) {
      consume().subscribe();
  }
}
