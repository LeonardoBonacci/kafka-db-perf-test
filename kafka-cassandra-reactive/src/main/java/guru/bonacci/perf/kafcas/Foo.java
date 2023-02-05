package guru.bonacci.perf.kafcas;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("foo_reactive")
@NoArgsConstructor
@AllArgsConstructor
public class Foo {

  @PrimaryKey
  private FooKey key;
}