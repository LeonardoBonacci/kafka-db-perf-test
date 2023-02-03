
package guru.bonacci.performance.kafcas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication 
@RequiredArgsConstructor
public class KafCasReactiveApp implements CommandLineRunner {

	private final ReactiveKafkaConsumerTemplate<String, String> kTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(KafCasReactiveApp.class, args);
	}
	
	private Mono<Void> consume() {
   return kTemplate
           .receiveAutoAck()
           .doOnNext(consumerRecord -> log.info("received value={}", consumerRecord.value()))
           .then();
	}
	
	@Override
  public void run(String... args) {
      consume().subscribe();
  }
}
