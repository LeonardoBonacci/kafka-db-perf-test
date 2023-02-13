package guru.bonacci.perf.kafcasreact;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CFooRepo extends ReactiveCassandraRepository<CFoo, CFooKey> {
}