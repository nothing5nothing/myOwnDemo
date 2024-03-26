package com.jcx.military.project.task.queue;


import com.jcx.military.project.task.data.SyncTask;
import com.jcx.military.project.task.data.SyncTaskFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class QueueConfig {

    public static int downloadBatch;

    public static int retryBatch;

    public static int packBatch;

    public static LinkedBlockingQueue<SyncTaskFile> downloadList ;

    public static LinkedBlockingQueue<SyncTaskFile> retryList ;

    public static LinkedBlockingQueue<SyncTask> packList;


    @Value("${batch.download:4}")
    public void setDownloadBatch(int downloadBatch) {
        QueueConfig.downloadBatch = downloadBatch;
        downloadList = new LinkedBlockingQueue<>(downloadBatch*2);
    }

    @Value("${batch.retry:1}")
    public void setRetryBatch(int retryBatch) {
        QueueConfig.retryBatch = retryBatch;
        retryList = new LinkedBlockingQueue<>(retryBatch*2);
    }

    @Value("${batch.pack:4}")
    public void setPackBatch(int packBatch) {
        QueueConfig.packBatch = packBatch;
        packList = new LinkedBlockingQueue<>(packBatch*2);
    }





}
