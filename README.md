# kafka-db-perf-test

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