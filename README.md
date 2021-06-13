# kafka-singlenode
## Start Kafka single node and zookeper
```
 docker-compose -f kafka-single-node.yml up -d
```
## Executea shell in Kafka Container
```
 docker exec -it kafka-broker /bin/bash
```
## Change directory to the Kafka Scripts
```
 cd /opt/bitnami/kafka/bin
```
## Create a Topic with 2 partitions and one replication factor
```
 ./kafka-topics.sh \
   --zookeeper zookeeper:2181 \
   --create \
   --topic kafka.singlenode.testtopic \
   --partitions 2 \
   --replication-factor 1
```
## List Topics
```
 ./kafka-topics.sh \
   --zookeeper zookeeper:2181 \
   --list
```
## Publish Messages to Topics
```
  ./kafka-console-producer.sh \
   --bootstrap-server localhost:29092 \
   --topic kafka.singlenode.testtopic
```
## Consume Messages from Topics
```
  ./kafka-console-consumer.sh \
   --bootstrap-server localhost:29092 \
   --topic kafka.singlenode.testtopic \
   --from-beginning
```