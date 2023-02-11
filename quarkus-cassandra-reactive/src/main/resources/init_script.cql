CREATE TABLE IF NOT EXISTS spring_cassandra.fruit(name text PRIMARY KEY, description text);

CREATE TABLE IF NOT EXISTS spring_cassandra.foo(bar text PRIMARY KEY, when bigint);


CREATE TABLE IF NOT EXISTS spring_cassandra.quarkus_foo (
    bar text,
    when bigint,
    PRIMARY KEY ((bar), when)
) WITH CLUSTERING ORDER BY (when DESC);


CREATE TABLE quarkus_foo(bar text, when bigint,
                             PRIMARY KEY(text, when));