package guru.bonacci.perf.transformer;

import java.util.Collections;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import guru.bonacci.perf.avro.Foo;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableKafkaStreams 
@SpringBootApplication
public class TransformerApp {

	public static void main(String[] args) {
		SpringApplication.run(TransformerApp.class, args);
	}

	long counter = 0l;
	
	@Bean
	public KStream<String, Foo> topology(StreamsBuilder builder) {
		final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
        "http://localhost:8081");
		
		final Serde<Foo> avroSerde = new SpecificAvroSerde<>();
		avroSerde.configure(serdeConfig, false); 

		
	  KStream<String, String> inputStream = 
     builder 
      .stream("perf_string", Consumed.with(Serdes.String(), Serdes.String()))
      .peek((poolFrom, transfer) -> log.info("in {}<>{}", poolFrom, transfer));
      
	  KStream<String, Foo> outputStream = 
	    inputStream
	    	.mapValues(uuid -> Foo.newBuilder().setId(counter++).setBar(uuid).setWhenn(System.currentTimeMillis()).build());

	  outputStream.to("perf_avroo", Produced.with(Serdes.String(), avroSerde));

	  return outputStream;
	}
}
