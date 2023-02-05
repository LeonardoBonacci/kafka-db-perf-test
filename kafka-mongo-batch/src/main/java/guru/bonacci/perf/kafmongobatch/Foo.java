package guru.bonacci.perf.kafmongobatch;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("foo_blocking_batch")
public class Foo {

	@Id
	private Long id;
	
  private String bar;
  private long whenn;
}