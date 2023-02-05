package guru.bonacci.perf.kafsql;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table("foo_reactive")
@NoArgsConstructor
@AllArgsConstructor
public class Foo {

	@Id
	@Column("id")
	private Long id;
	
	@Column("bar")
  private String bar;
	
	@Column("whenn")
  private long whenn;
}