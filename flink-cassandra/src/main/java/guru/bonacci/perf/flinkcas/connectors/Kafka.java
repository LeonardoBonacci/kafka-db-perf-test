package guru.bonacci.perf.flinkcas.connectors;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

public class Kafka {

  public static FlinkKafkaConsumer<String> createStringConsumerForTopic(String topic, String kafkaAddress, String kafkaGroup) {
      Properties props = new Properties();
      props.setProperty("bootstrap.servers", kafkaAddress);
      props.setProperty("group.id", kafkaGroup);
      var consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props);

      return consumer;
  }
}