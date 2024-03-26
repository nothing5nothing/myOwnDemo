package com.jcx.military.project.task.registry;

import com.jcx.military.project.task.consumer.TaskConsumer;
import com.jcx.military.project.task.consumer.impl.FileDownloadConsumer;
import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.task.producer.FileDownloadProducer;
import com.jcx.military.project.task.queue.QueueConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

@Component
public class DownloadFileRegister extends TaskRegistry<SyncTaskFile>{

    private static int corePoolSize;

    private static int maximumPoolSize;

    private static int queueCapacity;

    int batch;

    @Autowired
    FileDownloadConsumer consumer;

    @Autowired
    FileDownloadProducer producer;

    public DownloadFileRegister(ApplicationContext context) {

        super(QueueConfig.downloadBatch, QueueConfig.downloadList, QueueConfig.downloadBatch,
                new ThreadPoolExecutor(
                        corePoolSize = Integer.parseInt(context.getEnvironment().getProperty("task.download.core")),
                        maximumPoolSize = Integer.parseInt(context.getEnvironment().getProperty("task.download.max")),
                        2L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(
                        queueCapacity= Integer.parseInt(context.getEnvironment().getProperty("task.download.capacity")))) );

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
