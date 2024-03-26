//package com.jcx.military.project.task.factory;
//
//import com.jcx.military.project.task.consumer.AbsTaskConsumer;
//import com.jcx.military.project.task.consumer.impl.FileDownloadConsumer;
//import com.jcx.military.project.task.consumer.impl.FilePackConsumer;
//import com.jcx.military.project.task.consumer.impl.FileRetryConsumer;
//import com.jcx.military.project.task.producer.FileDownloadProducer;
//import com.jcx.military.project.task.producer.FileRetryProducer;
//import com.jcx.military.project.task.producer.Producer;
//import com.jcx.military.project.task.producer.TaskPackProducer;
//import com.jcx.military.project.task.queue.QueueConfig;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.Semaphore;
//
//@Component
//public class TaskFactory {
//
//    @Autowired
//    TaskPackProducer taskPackProducer;
//
//    @Autowired
//    FileRetryProducer fileRetryProducer;
//
//    @Autowired
//    FileDownloadProducer fileDownloadProducer;
//
//    @Autowired
//    FilePackConsumer filePackConsumer;
//
//    @Autowired
//    FileDownloadConsumer fileDownloadConsumer;
//
//    @Autowired
//    FileRetryConsumer fileRetryConsumer;
//
//    Map<TaskEnum, AbsTaskConsumer> consumers = new HashMap<>();
//
//    Map<TaskEnum, Producer> producers = new HashMap<>();
//
//    Map<TaskEnum, LinkedBlockingQueue> queues = new HashMap<>();
//
//
//    Map<TaskEnum, Semaphore> semaphoreMap = new HashMap<>();
//
//    @PostConstruct
//    public void init(){
//        consumers.put(TaskEnum.pack, filePackConsumer);
//        consumers.put(TaskEnum.download, fileDownloadConsumer);
//        consumers.put(TaskEnum.retry, fileRetryConsumer);
//
//        producers.put(TaskEnum.pack, taskPackProducer);
//        producers.put(TaskEnum.download, fileDownloadProducer);
//        producers.put(TaskEnum.retry, fileRetryProducer);
//
//        semaphoreMap.put(TaskEnum.pack, new Semaphore(QueueConfig.packBatch));
//        semaphoreMap.put(TaskEnum.download, new Semaphore(QueueConfig.downloadBatch));
//        semaphoreMap.put(TaskEnum.retry, new Semaphore(QueueConfig.retryBatch));
//
//        queues.put(TaskEnum.pack, QueueConfig.packList);
//        queues.put(TaskEnum.download, QueueConfig.downloadList);
//        queues.put(TaskEnum.retry, QueueConfig.retryList);
//
//    }
//
//    public Producer getProducer(TaskEnum e) {
//        return producers.get(e);
//    }
//
//    public LinkedBlockingQueue getQueue(TaskEnum e) {
//        return queues.get(e);
//    }
//    public AbsTaskConsumer getConsumer(TaskEnum e) {
//        return consumers.get(e);
//    }
//
//    public Semaphore getResource(TaskEnum e) {
//        return semaphoreMap.get(e);
//    }
//
//
//
//}
