package com.dashboard.aop;

import com.dashboard.Producer;
import com.dashboard.modal.UserRequestDetails;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Request Response Client ------> AspectAspect Oriented-Programming
 */
@Aspect
@Component
public class Procucer_AOP {
    final static Logger logger = LoggerFactory.getLogger(Producer.class);
    private UserRequestDetails userRequestDetails;

    public Procucer_AOP() {
    }
    @Before("@annotation(com.dashboard.aop.PROD)")
    public void sendUserActivity(JoinPoint joinPoint) throws Throwable {
        System.out.println("before method execution");
    }
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Producer.class);
        //Logger logger = LoggerFactory.getLogger(Consumer.class);
        String groupId = "FirstApplication";
        String topic = "userDetails";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //----------> Create kafka
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("Key___:" + record.key() + "\n");
                logger.info("Value___:" + record.value() + "\n");
                logger.info("Topic___:" + record.topic() + "\n");
                logger.info("Offset___:" + record.offset() + "\n");

            }
        }
    }}


