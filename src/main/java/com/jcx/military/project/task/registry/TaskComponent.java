package com.jcx.military.project.task.registry;

import com.jcx.military.project.kafka.producer.MyKafkaProducer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class TaskComponent {

    public static final Logger log = LoggerFactory.getLogger(TaskComponent.class);

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4,10,10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    @Autowired
    MyKafkaProducer myKafkaProducer;

    @PostConstruct
    public void init () {
        log.error("WatchDog2 run");
//        executor.execute(new WatchDog2());

//        kafkaProducer.sendMessage(UploadInfo.uploadInfoDefault.getInfo());
    }
}
