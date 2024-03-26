package com.jcx.military.project.task.consumer.impl;

import com.jcx.military.project.task.consumer.AbsTaskConsumer;
import com.jcx.military.project.task.data.SyncTask;
import com.jcx.military.project.task.producer.FileProducer;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@TaskAn(type = TaskType.PACK, count= 2)
public class FilePackConsumer extends AbsTaskConsumer<SyncTask> {
    Logger log = LoggerFactory.getLogger(FilePackConsumer.class);
    @Override
    public void consumer(SyncTask task) {
        log.error("consumer:"+ task.toString());
        // 文件获取， 文件打包， 修改数据状态， 文件加密
        sleep();

    }

}
