package com.dashboard.aop;

import com.dashboard.modal.UserDetails;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer_AOP {
    final static Logger logger = LoggerFactory.getLogger(Producer_AOP.class);
    public static void main(String[] args) {
        //----------> Config  kafka Producer
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        //----------> Create kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        //----------> Create  Producer record
        UserDetails user = new UserDetails();
        user.setCountry("tn");
        ProducerRecord<String,String > record =
                new ProducerRecord("userDetails", user.toString());
        //----------> Send data
        producer.send(record, (recordMetadata, e) -> {
            if (e == null){
                logger.info("Received new metadata. \n");
                logger.info( "1-Topic____:"+ recordMetadata.topic()+"\n");
                logger.info("2- Offset____:"+ recordMetadata.offset() +"\n");
                logger.info( "3- TimeStamp____:"+ recordMetadata.timestamp() +"\n");
                logger.info("4- Partition____:"+ recordMetadata.partition()+"\n");

            } else {
                logger.error("Error while Producing data ",e);

            }
        });
        producer.flush();
        producer.close();
    }
}
