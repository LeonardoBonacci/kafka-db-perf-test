package guru.bonacci.perf.kafmongoreactive;

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
@Document("foo_reactive")
public class Foo {

	@Id
	private Long id;
	
  private String bar;
  private long whenn;
}