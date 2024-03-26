package com.jcx.military.project.task.registry;

import com.jcx.military.project.task.consumer.impl.FileDownloadConsumer;
import com.jcx.military.project.task.consumer.impl.FileRetryConsumer;
import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.task.producer.FileDownloadProducer;
import com.jcx.military.project.task.producer.FileRetryProducer;
import com.jcx.military.project.task.queue.QueueConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class RetryRegister extends TaskRegistry<SyncTaskFile>{


    private static int corePoolSize;

    private static int maximumPoolSize;

    private static int queueCapacity;

    @Autowired
    FileRetryConsumer consumer;

    @Autowired
    FileRetryProducer producer;

    public RetryRegister(ApplicationContext context) {
        super(QueueConfig.retryBatch, QueueConfig.retryList, QueueConfig.retryBatch,
                new ThreadPoolExecutor(
                        corePoolSize = Integer.parseInt(context.getEnvironment().getProperty("task.retry.core")),
                        maximumPoolSize = Integer.parseInt(context.getEnvironment().getProperty("task.retry.max")),
                        2L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(
                        queueCapacity= Integer.parseInt(context.getEnvironment().getProperty("task.retry.capacity")))) );
    }

    @Override
    List<SyncTaskFile> getProducer() {
        return producer.produce(batch);
    }

    @Override
    void getCosumer(SyncTaskFile syncTaskFile) {
        consumer.consumer(syncTaskFile);
    }
}
