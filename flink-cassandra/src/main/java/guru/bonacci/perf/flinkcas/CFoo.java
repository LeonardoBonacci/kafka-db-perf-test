package guru.bonacci.perf.flinkcas;


import java.io.Serializable;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(keyspace = "spring_cassandra", name = "test_table")
public class CFoo implements Serializable {

    private static final long serialVersionUID = 1123119384361005680L;

    @Column(name = "bar")
    private String bar;

    @Column(name = "when")
    private Long when;
}
