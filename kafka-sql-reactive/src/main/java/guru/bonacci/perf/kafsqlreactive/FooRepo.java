package guru.bonacci.perf.kafsqlreactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends ReactiveCrudRepository<Foo, Long> {
}