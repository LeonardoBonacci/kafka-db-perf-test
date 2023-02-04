# kafka-db-perf-test

```
docker exec -it cassandra cqlsh

CREATE KEYSPACE IF NOT EXISTS spring_cassandra WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};
```


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