package guru.bonacci.perf.kafcasbatch;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("foo_blocking_batch")
@NoArgsConstructor
@AllArgsConstructor
public class Foo {

  @PrimaryKey
  private FooKey key;
}