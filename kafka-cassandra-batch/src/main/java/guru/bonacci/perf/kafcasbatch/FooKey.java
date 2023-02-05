package guru.bonacci.perf.kafcasbatch;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@PrimaryKeyClass
@NoArgsConstructor
@AllArgsConstructor
public class FooKey {

  @PrimaryKeyColumn(name = "bar", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private String bar;

  @PrimaryKeyColumn(
      name = "when", 
      ordinal = 1, 
      type = PrimaryKeyType.CLUSTERED)
  private long when;
}