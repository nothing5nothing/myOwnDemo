package com.jcx.military.project.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    private static final Logger log = LoggerFactory.getLogger(ConsumerListener.class);

//    @KafkaListener(topics = "boot-kafka-topic")
    public void listenUser (ConsumerRecord<?,String> record, Acknowledgment acknowledgment) {
        try {
            String key =  String.valueOf(record.key());
            String body = record.value();
            log.info("\n=====\ntopic:boot-kafka-topic，key{}，body:{}\n=====\n",key,body);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
