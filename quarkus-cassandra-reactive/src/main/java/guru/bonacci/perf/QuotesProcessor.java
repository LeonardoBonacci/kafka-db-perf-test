package guru.bonacci.perf;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;

@ApplicationScoped
public class QuotesProcessor {

  @Incoming("prices")
  public void process(String quoteRequest) throws InterruptedException {
  	Log.info(quoteRequest);
  }
}