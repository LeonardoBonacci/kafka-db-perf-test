package guru.bonacci.perf.kafcas;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CFooRepo extends CassandraRepository<CFoo, CFooKey> {
}