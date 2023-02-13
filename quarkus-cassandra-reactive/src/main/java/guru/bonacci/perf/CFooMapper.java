package guru.bonacci.perf;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface CFooMapper {

  @DaoFactory
  ReactiveCFooDao reactiveCFooDao();
}
