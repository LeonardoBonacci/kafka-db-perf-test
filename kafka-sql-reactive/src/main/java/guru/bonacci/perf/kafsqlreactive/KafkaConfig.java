package guru.bonacci.perf.kafsqlreactive;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class KafkaConfig {
    
	@Bean
  public ReceiverOptions<String, String> kafkaReceiverOptions(KafkaProperties kafkaProperties) {
    ReceiverOptions<String, String> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
    return basicReceiverOptions.subscription(List.of("topic-perf"));
  }

  @Bean
  public ReactiveKafkaConsumerTemplate<String, String> reactKafkaConsumerTemplate(
  		ReceiverOptions<String, String> kafkaReceiverOptions) {
    return new ReactiveKafkaConsumerTemplate<String, String>(kafkaReceiverOptions);
  }
}