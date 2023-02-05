package guru.bonacci.perf.kafmongobatch;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends MongoRepository<Foo, Long> {
}