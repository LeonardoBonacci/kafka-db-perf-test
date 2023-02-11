package guru.bonacci.perf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ReactiveFruitService {

  @Inject ReactiveCFooDao fruitDao;

//  public Uni<Void> add(Fruit fruit) {
//    return fruitDao.update(fruit);
//  }
  
  public Uni<Void> add(CFoo foo) {
    return fruitDao.insert(foo);
  }
}