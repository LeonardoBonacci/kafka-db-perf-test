package guru.bonacci.performance.kafcasbatch;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends CassandraRepository<Foo, FooKey> {
}