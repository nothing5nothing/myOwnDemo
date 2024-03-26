package com.jcx.military.project.kafka.producer;

import com.amazonaws.services.s3.AmazonS3EncryptionClientBuilder;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jcx.military.project.kafka.entiry.FileInfo;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class MyKafkaProducer {

    Logger log = LoggerFactory.getLogger(MyKafkaProducer.class);

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    public void sendMessage(FileInfo info) {


        JsonMapper jsonMapper = new JsonMapper();


        Map<String, Object> props = kafkaTemplate.getProducerFactory().getConfigurationProperties();
        Map<String,Object> prop = new HashMap<>(props);
//                    prop.put(ProducerConfig.LINGER_MS_CONFIG, 1000000L);
        kafkaTemplate.getProducerFactory().updateConfigs(prop);


        int count = 100000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, 1000, 1L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100000));
        for(int i =0;i<count;i++) {
            int finalI = i;
            executor.execute(()->{
                try {

                    info.setId(finalI);
                    String msg = jsonMapper.writeValueAsString(info);
                    String topic = "test";
                    String key = "key";
                    // 发送消息
                    kafkaTemplate.send(topic,key,msg);
                    kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
                        @Override
                        public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
                            log.error(producerRecord.toString()+"success");
                        }

                        @Override
                        public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                            log.error(producerRecord.toString()+"error");
                        }
                    });
                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();

                }
            });
        }
        try{
            countDownLatch.await();
        }catch (Exception e) {
            log.error(e.toString());
        }
        log.error("end");

//        try {
//            Map<String, Object> props = kafkaTemplate.getProducerFactory().getConfigurationProperties();
//            Map<String,Object> prop = new HashMap<>(props);
//            prop.put(ProducerConfig.LINGER_MS_CONFIG, 1000000000000L);
//            kafkaTemplate.getProducerFactory().updateConfigs(prop);
//            String msg = jsonMapper.writeValueAsString(info);
//            String topic = "test";
//            String key = "key";
//            // 发送消息
//            kafkaTemplate.send(topic,key,msg);
//            kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
//                @Override
//                public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
//                    log.error(producerRecord.toString()+"success");
//                }
//
//                @Override
//                public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata, Exception exception) {
//                    log.error(producerRecord.toString()+"error");
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }



    }

    /**
     * 2 点多版本的回调方法
     * public void sendMessage(String msg) {
     *     LOGGER.info("===Producing message[{}]: {}", mTopic, msg);
     *     ListenableFuture<SendResult<String, String>> future = mKafkaTemplate.send(mTopic, msg);
     *     future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
     *         @Override
     *         public void onSuccess(SendResult<String, String> result) {
     *             LOGGER.info("===Producing message success");
     *         }
     *
     *         @Override
     *         public void onFailure(Throwable ex) {
     *             LOGGER.info("===Producing message failed");
     *         }
     *
     *     });
     * }
     */

}
