package guru.bonacci.perf.kafcasreact;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;

import guru.bonacci.perf.avro.Foo;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class KafkaConfig {
    
	@Bean
  public ReceiverOptions<String, Foo> kafkaReceiverOptions(KafkaProperties kafkaProperties) {
    ReceiverOptions<String, Foo> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
    return basicReceiverOptions.subscription(List.of("perf_avro"));
  }

  @Bean
  public ReactiveKafkaConsumerTemplate<String, Foo> reactKafkaConsumerTemplate(
  		ReceiverOptions<String, Foo> kafkaReceiverOptions) {
    return new ReactiveKafkaConsumerTemplate<String, Foo>(kafkaReceiverOptions);
  }
}