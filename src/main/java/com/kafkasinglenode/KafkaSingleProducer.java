package com.kafkasinglenode;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.record.CompressionType;

import java.util.Properties;
import java.util.Random;

public class KafkaSingleProducer {
    public static void main(String[] args) {

        //Setup Properties for Kafka Producer
        Properties kafkaProps = new Properties();

        //List of brokers to connect to
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");

        //Serializer class used to convert Keys to Byte Arrays
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        //Serializer class used to convert Messages to Byte Arrays
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        //Set ACKS to all so all replicas needs to acknolwedge
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "all");

        //Set compression type to GZIP
        kafkaProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,
                CompressionType.GZIP.name);

        //Create a Kafka producer from configuration
        KafkaProducer simpleProducer = new KafkaProducer(kafkaProps);

        //Publish 10 messages at 1 second intervals, with a random key
        try {
            int startKey = (new Random()).nextInt(1000);
            for (int i = startKey; i < startKey + 10; i++) {

                //Create a producer Record
                ProducerRecord<String, String> kafkaRecord =
                        new ProducerRecord<String, String>(
                                "kafka.singlenode.testtopic",    //Topic name
                                String.valueOf(i),          //Key for the message
                                "This is message " + i         //Message Content
                        );

                System.out.println("Sending Message : " + kafkaRecord.toString());

                //Publish to Kafka with Callback on Producer
                simpleProducer.send(kafkaRecord, new KafKaProducerCallBack(String.valueOf(i)));

                Thread.sleep(1000);
            }
        } catch (Exception e) {

        } finally {
            simpleProducer.close();
        }
    }
}
