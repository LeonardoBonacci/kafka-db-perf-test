package guru.bonacci.perf.kafsqlbatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepo extends JpaRepository<Foo, Long> {
}