package guru.bonacci.perf;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;

import io.smallrye.mutiny.Uni;

@Dao
public interface ReactiveCFooDao {

  @Insert
  Uni<Void> insert(CFoo foo);
}