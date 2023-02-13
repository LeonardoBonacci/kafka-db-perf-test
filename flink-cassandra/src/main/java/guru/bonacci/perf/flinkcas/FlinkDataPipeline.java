package guru.bonacci.perf.flinkcas;


import java.util.ArrayList;
import java.util.UUID;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.cassandra.CassandraSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import guru.bonacci.perf.flinkcas.connectors.Kafka;

public class FlinkDataPipeline {

	 private static final ArrayList<CFoo> messages = new ArrayList<>(20);

   static {
       for (long i = 0; i < 20; i++) {
           messages.add(new CFoo(UUID.randomUUID().toString(), System.currentTimeMillis()));
       }
   }
   
    public static void capitalize() throws Exception {
        String inputTopic = "flink_input";
        String consumerGroup = "baeldung";
        String address = "localhost:9092";

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        FlinkKafkaConsumer<String> flinkKafkaConsumer = Kafka.createStringConsumerForTopic(inputTopic, address, consumerGroup);
        flinkKafkaConsumer.setStartFromEarliest();

        DataStream<CFoo> source = environment.addSource(flinkKafkaConsumer)
        		.map(uuid -> new CFoo(uuid, System.currentTimeMillis()));
        
        CassandraSink.addSink(source)
	        .setHost("127.0.0.1")
	        .build();

        environment.execute();
    }

    public static void main(String[] args) throws Exception {
        capitalize();
    }

}