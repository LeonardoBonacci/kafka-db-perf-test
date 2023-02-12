package guru.bonacci.perf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class FooProcessor {


  @Inject ReactiveCFooDao cfooDao;

  @Incoming("foo")
  public Uni<Void> process(String foo) throws InterruptedException {
    return cfooDao.insert(new CFoo(foo, System.currentTimeMillis()));
  }
}