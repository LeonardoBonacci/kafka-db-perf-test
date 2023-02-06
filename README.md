# Prepare Kafka

```
./bin/kafka-topics --bootstrap-server localhost:9092 --list
./bin/kafka-topics --bootstrap-server localhost:9092 --topic perf_string --create --partitions 1 --replication-factor 1
./bin/kafka-topics --bootstrap-server localhost:9092 --topic perf_avro --create --partitions 1 --replication-factor 1

{"type": "record","name": "Foo", "namespace": "guru.bonacci.perf.avro", "fields": [{"name": "id", "type": "long"}, {"name": "bar","type": "string"}]}

{\"type\": \"record\",\"name\": \"Foo\", \"namespace\": \"guru.bonacci.perf.avro\", \"fields\": [{\"name\": \"id\", \"type\": \"long\"}, {\"name\": \"bar\",\"type\": \"string\"}]}

curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"schema": "{\"type\": \"record\",\"name\": \"Foo\", \"namespace\": \"guru.bonacci.perf.avro\", \"fields\": [{\"name\": \"id\", \"type\": \"long\"}, {\"name\": \"bar\",\"type\": \"string\"}]}"}' \
  http://localhost:8081/subjects/test-avro-value/versions

curl -X DELETE http://localhost:8081/subjects/perf_avro-value/versions/latest


./bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic perf_string --from-beginning

./bin/kafka-avro-console-consumer --bootstrap-server localhost:9092 --topic perf_avro --from-beginning \  
    --property schema.registry.url="http://schema-registry:8081"


./bin/kafka-console-producer --bootstrap-server localhost:9092 --topic test_topic

kafka-avro-console-producer \
 --broker-list localhost:9092 \
 --topic test-avro  \
 --property schema.registry.url=http://localhost:8081 \
 --property value.schema.id=4

 ./bin/kafka-avro-console-consumer --bootstrap-server localhost:9092 --topic test-avro --from-beginning \  
    --property schema.registry.url="http://schema-registry:8081"
```


# Cassandra

```
docker-compose -f docker-compose.yml -f cassandra/docker-compose-cas.yml up -d
docker exec -it cassandra cqlsh

CREATE KEYSPACE IF NOT EXISTS spring_cassandra WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

./bin/connect-standalone etc/kafka/connect-standalone.properties connect-cassandra-avro-sink.properties
```


```
foo@bar confluent-7.2.2 % ./bin/kafka-producer-perf-test \       
    --topic perf_string \
    --num-records 10 \
    --record-size 100 \
    --throughput -1 \
    --producer-props \
        bootstrap.servers=localhost:9092 \
        batch.size=200000 \
        linger.ms=100 \
        compression.type=lz4 \
        acks=1
        
./bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic topic-perf --from-beginning
```


```
cqlsh:spring_cassandra> select max(when) - min(when) from spring_cassandra.foo_blocking;

 system.max(when) - system.min(when)
-------------------------------------
                             1.274.547


cqlsh:spring_cassandra> select max(when) - min(when) from spring_cassandra.foo_blocking_batch;

 system.max(when) - system.min(when)
-------------------------------------
                             1.381.360


cqlsh> select max(when) - min(when) from spring_cassandra.foo_reactive;

 system.max(when) - system.min(when)
-------------------------------------
                               45.245
```

# SQL
```
foo@bar confluent-7.2.2 % ./bin/kafka-producer-perf-test \       
    --topic topic-perf \
    --num-records 10 \
    --record-size 100 \
    --throughput -1 \
    --producer-props \
        bootstrap.servers=localhost:9092 \
        batch.size=200000 \
        linger.ms=100 \
        compression.type=lz4 \
        acks=1
        
./bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic topic-perf --from-beginning
```


```
postgres=# select max(whenn) - min(whenn) from foo_blocking;
 ?column?
----------
  1.430.336

postgres=# select max(whenn) - min(whenn) from foo_blocking_batch;
 ?column?
----------
   708.419

postgres=# select max(whenn) - min(whenn) from foo_reactive;
 ?column?
----------
   264.188
```

# MongoDB

```
test> db.foo_blocking.find().sort({"_id":1}).limit(1)
[
  {
    _id: Long("0"),
    bar: 'SSXVNJHPDQDXVCRASTVYBCWVMGNYKRXVZXKGXTSPSJDGYLUEGQFLAQLOCFLJBEPOWFNSOMYARHAOPUFOJHHDXEHXJBHWGSMZJGNL',
    whenn: Long("1675560090442"),
    _class: 'guru.bonacci.perf.kafmongobatch.Foo'
  }
]

test> db.foo_blocking.find().sort({"_id":-1}).limit(1)
[
  {
    _id: Long("999999"),
    bar: 'ZBOPOQZYGATIOIEIYXCTVOQWNXSKVHPSPEVTLTDNBQTHVVVEBGZOCVTBAUBDNPMPLICFOBULZEMLLAMTKWGORFSCDHNAWTSTOEIB',
    whenn: Long("1675560989836"),
    _class: 'guru.bonacci.perf.kafmongobatch.Foo'
  }
]

1675560989836 - 1675560090442 = 899.394
```

```
test> db.foo_blocking_batch.find().sort({"_id":1}).limit(1)
[
  {
    _id: Long("0"),
    bar: 'SSXVNJHPDQDXVCRASTVYBCWVMGNYKRXVZXKGXTSPSJDGYLUEGQFLAQLOCFLJBEPOWFNSOMYARHAOPUFOJHHDXEHXJBHWGSMZJGNL',
    whenn: Long("1675562143146"),
    _class: 'guru.bonacci.perf.kafmongobatch.Foo'
  }
]

test> db.foo_blocking_batch.find().sort({"_id":-1}).limit(1)
[
  {
    _id: Long("999999"),
    bar: 'ZBOPOQZYGATIOIEIYXCTVOQWNXSKVHPSPEVTLTDNBQTHVVVEBGZOCVTBAUBDNPMPLICFOBULZEMLLAMTKWGORFSCDHNAWTSTOEIB',
    whenn: Long("1675563040245"),
    _class: 'guru.bonacci.perf.kafmongobatch.Foo'
  }
]

1675563040245 - 1675560989836 =  2.020.409
```

```
 db.foo_reactive.find().sort({"_id":-1}).limit(1)
[
  {
    _id: Long("999999"),
    bar: 'ZBOPOQZYGATIOIEIYXCTVOQWNXSKVHPSPEVTLTDNBQTHVVVEBGZOCVTBAUBDNPMPLICFOBULZEMLLAMTKWGORFSCDHNAWTSTOEIB',
    whenn: Long("1675559682989"),
    _class: 'guru.bonacci.perf.kafmongoreactive.Foo'
  }
]

test> db.foo_reactive.find().sort({"_id":1}).limit(1)
[
  {
    _id: Long("0"),
    bar: 'SSXVNJHPDQDXVCRASTVYBCWVMGNYKRXVZXKGXTSPSJDGYLUEGQFLAQLOCFLJBEPOWFNSOMYARHAOPUFOJHHDXEHXJBHWGSMZJGNL',
    whenn: Long("1675559610697"),
    _class: 'guru.bonacci.perf.kafmongoreactive.Foo'
  }
]

1675559682989 - 1675559610697 = 72.292
```