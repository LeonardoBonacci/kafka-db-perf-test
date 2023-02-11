package guru.bonacci.perf;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

/**
 * An object mapper defining all the DAOs for this application.
 *
 * <p>Classes annotated with {@link Mapper} are the main entry point for the DataStax Java driver
 * object mapper.
 *
 * <p>An application-scoped {@link FruitMapper} bean will be automatically created and can be
 * injected everywhere in your application.
 *
 * @see <a
 *     href="https://docs.datastax.com/en/developer/java-driver/latest/manual/mapper/mapper/">Using
 *     the Mapper interface</a>
 */
@Mapper
public interface FruitMapper {

  /**
   * Creates a new {@link ReactiveFruitDao}. It will operate on the same keyspace as the Quarkus
   * session.
   *
   * <p>The application-scoped {@link FruitDao} bean produced by this method can be automatically
   * injected everywhere in your application.
   *
   * @return a new {@link ReactiveFruitDao}.
   */
  @DaoFactory
  ReactiveFruitDao reactiveFruitDao();
}
