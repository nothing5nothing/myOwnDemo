package com.jcx.military.project.task.registry;

import com.jcx.military.project.task.consumer.impl.FileDownloadConsumer;
import com.jcx.military.project.task.consumer.impl.FilePackConsumer;
import com.jcx.military.project.task.data.SyncTask;
import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.task.producer.FileDownloadProducer;
import com.jcx.military.project.task.producer.TaskPackProducer;
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
public class PackRegister extends TaskRegistry<SyncTask>{


    private static int corePoolSize;

    private static int maximumPoolSize;

    private static int queueCapacity;

    @Autowired
    FilePackConsumer consumer;

    @Autowired
    TaskPackProducer producer;

    public PackRegister(ApplicationContext context) {

        super(QueueConfig.packBatch, QueueConfig.packList, QueueConfig.packBatch,
                new ThreadPoolExecutor(
                        corePoolSize = Integer.parseInt(context.getEnvironment().getProperty("task.pack.core")),
                        maximumPoolSize = Integer.parseInt(context.getEnvironment().getProperty("task.pack.max")),
                        2L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(
                        queueCapacity= Integer.parseInt(context.getEnvironment().getProperty("task.pack.capacity")))) );
    }

    @Override
    List<SyncTask> getProducer() {
        return producer.produce(batch);
    }

    @Override
    void getCosumer(SyncTask syncTask) {
        consumer.consumer(syncTask);
    }
}