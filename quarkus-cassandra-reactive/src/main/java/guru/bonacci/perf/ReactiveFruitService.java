package guru.bonacci.perf;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * A service that manages {@link Fruit} objects using reactive-style programming. This service
 * leverages the {@link ReactiveFruitDao} DAO.
 */
@ApplicationScoped
public class ReactiveFruitService {

  @Inject ReactiveFruitDao fruitDao;

  public Uni<Void> add(Fruit fruit) {
    return fruitDao.update(fruit);
  }

  public Multi<Fruit> getAll() {
    return fruitDao.findAll();
  }
}