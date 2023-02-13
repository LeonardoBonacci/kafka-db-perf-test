CREATE TABLE IF NOT EXISTS spring_cassandra.fruit(name text PRIMARY KEY, description text);


CREATE TABLE IF NOT EXISTS quarkus_foo (bar text, when bigint, PRIMARY KEY(bar, when));