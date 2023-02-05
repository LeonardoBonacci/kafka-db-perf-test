package guru.bonacci.perf.kafmongoreactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends ReactiveMongoRepository<Foo, Long> {
}