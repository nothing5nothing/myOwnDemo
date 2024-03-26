package com.jcx.military.project.task.producer;

import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.task.queue.QueueConfig;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import org.springframework.stereotype.Service;

import java.util.List;


@TaskAn(type = TaskType.Download, count= 1)
@Service
public class FileDownloadProducer extends FileProducer {

    public FileDownloadProducer() {
//        super(QueueConfig.downloadBatch, QueueConfig.downloadList, 0);
        super(0);
    }

    @Override
    public List<SyncTaskFile> produce(int batch) {
        List<SyncTaskFile> list =  taskService.selectTaskFile(fileStatus, batch);
        log.error(list == null ? "生产 null": "生产：" + list.size());
        return list;
    }

}
