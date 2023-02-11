package guru.bonacci.perf;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;

/**
 * A REST resource exposing reactive endpoints for creating and retrieving {@link Fruit} objects in
 * the database, leveraging the {@link ReactiveFruitService} component.
 */
@Path("/reactive-fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveFruitResource {

  @Inject ReactiveFruitService service;

//  @GET
//  public Multi<FruitDto> getAll() {
//    return service.getAll().map(this::convertToDto);
//  }

  @GET
  public Uni<Void> foo() {
    return service.add(new Fruit("hi", "apple"));
  }

  @POST
  public Uni<Void> add(FruitDto fruitDto) {
    return service.add(convertFromDto(fruitDto));
  }

  private FruitDto convertToDto(Fruit fruit) {
    return new FruitDto(fruit.getName(), fruit.getDescription());
  }

  private Fruit convertFromDto(FruitDto fruitDto) {
    return new Fruit(fruitDto.getName(), fruitDto.getDescription());
  }
}