package guru.bonacci.perf.plaincas.cas;


import java.util.stream.Stream;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.DefaultBatchType;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CFooRepository {

    private final CqlSession session;

    public void insert(CFoo foo) {
      RegularInsert insertInto = QueryBuilder.insertInto("test_table")
        .value("bar", QueryBuilder.bindMarker())
        .value("when", QueryBuilder.bindMarker());

      SimpleStatement insertStatement = insertInto.build().setKeyspace(CqlIdentifier.fromCql("spring_cassandra"));
      PreparedStatement preparedStatement = session.prepare(insertStatement);

      BoundStatement statement = preparedStatement.bind()
        .setString(0, foo.getBar())
        .setLong(1, foo.getWhen());

      session.execute(statement);
    }
    
    public void insertBatch(Stream<CFoo> foos) {
    	final RegularInsert insertInto = QueryBuilder.insertInto("test_table")
        .value("bar", QueryBuilder.bindMarker())
        .value("when", QueryBuilder.bindMarker());

      final SimpleStatement insertStatement = insertInto.build().setKeyspace(CqlIdentifier.fromCql("spring_cassandra"));
      final PreparedStatement preparedStatement = session.prepare(insertStatement);

      var statements = foos.map(foo -> 
        preparedStatement.bind()
            .setString(0, foo.getBar())
            .setLong(1, foo.getWhen())
      ).toArray(BoundStatement[]::new);

      BatchStatement batchStatement = BatchStatement.newInstance(DefaultBatchType.UNLOGGED, statements);

      session.execute(batchStatement);
    }
}
