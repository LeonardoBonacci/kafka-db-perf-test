package guru.bonacci.perf;

import java.util.Objects;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;

@Entity
@PropertyStrategy(mutable = false)
@CqlName("quarkus_foo")
public class CFoo {

  @PartitionKey 
  private final String bar;

  @ClusteringColumn(1) 
  private final Long when;

  public CFoo(String bar, Long when) {
    this.bar = bar;
    this.when = when;
  }

  public String getBar() {
    return bar;
  }

  public Long getWhen() {
    return when;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CFoo that = (CFoo) o;
    return Objects.equals(bar, that.bar) && Objects.equals(bar, that.bar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bar, bar);
  }

  @Override
  public String toString() {
    return String.format("CFoo{bar='%s', when='%s'}", bar, when);
  }
}
