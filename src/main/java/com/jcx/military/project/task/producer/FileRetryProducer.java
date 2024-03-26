package com.jcx.military.project.task.producer;

import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import org.springframework.stereotype.Component;

@TaskAn(type = TaskType.RETRY, count= 1)
@Component
public class FileRetryProducer extends FileProducer{

    public FileRetryProducer() {
//        super(QueueConfig.downloadBatch, QueueConfig.downloadList, 3);
        super(3);
    }
}
