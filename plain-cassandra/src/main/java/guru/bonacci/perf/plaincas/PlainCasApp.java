package guru.bonacci.perf.plaincas;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.datastax.oss.driver.api.core.CqlSession;

import guru.bonacci.perf.avro.Foo;
import guru.bonacci.perf.plaincas.cas.CFoo;
import guru.bonacci.perf.plaincas.cas.CFooRepository;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

public class PlainCasApp {

  public static void main(String[] args) {
    new PlainCasApp().run();
  }	
  
  public void run() {
		CassandraConnector connector = new CassandraConnector();
	  connector.connect("127.0.0.1", 9042, "datacenter1");
	  CqlSession session = connector.getSession();
	  CFooRepository repo = new CFooRepository(session);

  	Properties props = new Properties();

  	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
  	props.put(ConsumerConfig.GROUP_ID_CONFIG, "i-am-plain-cassandra-2");
  	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
  	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
  	props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
  	props.put("schema.registry.url", "http://localhost:8081");


  	String topic = "test-avro";
  	final Consumer<String, Foo> consumer = new KafkaConsumer<String, Foo>(props);
  	consumer.subscribe(Arrays.asList(topic));

  	try {
  	  while (true) {
  	    ConsumerRecords<String, Foo> records = consumer.poll(100);
  	    records.forEach(record -> repo.insert(new CFoo(record.value().getBar(), System.currentTimeMillis())));
  	    
//  	    var cfoos = StreamSupport.stream(records.spliterator(), false)
//  	    	.map(rec -> new CFoo(rec.value().getBar(), System.currentTimeMillis()));
//  	    repo.insertBatch(cfoos);
  	  }
  	} finally {
  		consumer.close();
  		connector.close();
  	}
  }
}
