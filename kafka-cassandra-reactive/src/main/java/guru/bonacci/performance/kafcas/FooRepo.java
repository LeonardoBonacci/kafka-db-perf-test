package guru.bonacci.performance.kafcas;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends ReactiveCassandraRepository<Foo, FooKey> {
}