# kafka-singlenode
## Start Kafka single node and zookeper
```
 docker-compose -f kafka-single-node.yml up -d
```
## Stop Kafka single node and zookeper
```
 docker-compose -f kafka-single-node.yml down
```
## Execute a shell in Kafka Container
```
 docker exec -it kafka-broker bash
```
## Change directory to the Kafka Scripts
```
 cd /opt/bitnami/kafka/bin
```
## Create a Topic with 2 partitions and one replication factor
```
 ./kafka-topics.sh \
   --bootstrap-server localhost:9092 \
   --create \
   --topic kafka.singlenode.testtopic \
   --partitions 2 \
   --replication-factor 1
```
## List Topics
```
 ./kafka-topics.sh \
   --bootstrap-server localhost:9092 \
   --list
```
## Publish Messages to Topics
```
  ./kafka-console-producer.sh \
   --bootstrap-server localhost:9092 \
   --topic kafka.singlenode.testtopic
```
## Consume Messages from Topics
```
  ./kafka-console-consumer.sh \
   --bootstrap-server localhost:9092 \
   --topic kafka.singlenode.testtopic \
   --from-beginning
```
