package guru.bonacci.perf;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;

@Path("/reactive-fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveFruitResource {

  @Inject ReactiveFruitService service;

//  @GET
//  public Uni<Void> foo() {
//    return service.add(new Fruit("hi", "apple"));
//  }

  @GET
  public Uni<Void> foo() {
    return service.add(new CFoo(UUID.randomUUID().toString(), System.currentTimeMillis()));
  }

//  @POST
//  public Uni<Void> add(FruitDto fruitDto) {
//    return service.add(convertFromDto(fruitDto));
//  }
//
//  private Fruit convertFromDto(FruitDto fruitDto) {
//    return new Fruit(fruitDto.getName(), fruitDto.getDescription());
//  }
}