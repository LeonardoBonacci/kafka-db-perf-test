package guru.bonacci.perf;

import java.util.Objects;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;

/**
 * Represents the name and description of a fruit.
 *
 * @see <a
 *     href="https://docs.datastax.com/en/developer/java-driver/latest/manual/mapper/entities/">Defining
 *     entities with the DataStax Java driver object mapper</a>
 */
@Entity
@PropertyStrategy(mutable = false)
public class Fruit {

  @PartitionKey private final String name;

  private final String description;

  public Fruit(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * @return The fruit name.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The fruit description.
   */
  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Fruit that = (Fruit) o;
    return Objects.equals(description, that.description) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, name);
  }

  @Override
  public String toString() {
    return String.format("Fruit{name='%s', description='%s'}", name, description);
  }
}
