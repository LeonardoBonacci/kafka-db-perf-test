package guru.bonacci.perf.kafcas;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends ReactiveCassandraRepository<Foo, FooKey> {
}