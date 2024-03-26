package com.jcx.military.project.task.consumer.impl;

import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@TaskAn(
        type = TaskType.RETRY,
        count = 2
)
public class FileRetryConsumer extends DownloadConsumer {
    Logger log = LoggerFactory.getLogger(FileRetryConsumer.class);

    public FileRetryConsumer() {
    }

    public void consumer(SyncTaskFile file) {
        this.log.error("consumer:" + file.toString());
        this.sleep();
    }
}