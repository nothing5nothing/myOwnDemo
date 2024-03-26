package com.jcx.military.project.task.producer;

import com.jcx.military.project.task.data.SyncTask;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import com.jcx.military.project.task.registry.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@TaskAn(type = TaskType.PACK, count= 1)
@Component
public class TaskPackProducer implements Producer<SyncTask>{

    @Autowired
    TaskService taskService;

//    public TaskPackProducer() {
//        this.batch = QueueConfig.packBatch;
//        LinkedBlockingQueue = QueueConfig.packList;
//    }

    @Override
    public List<SyncTask> produce(int batch) {
        return taskService.selectTask(batch);
    }

//    @Override
//    public void pull(List<SyncTask> syncTask) {
//        syncTask.forEach(file -> {
//            try {
//                LinkedBlockingQueue.put(file);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//
//    @Override
//    public void run() {
//        while(true) {
//            try{
//                if (LinkedBlockingQueue.size() < batch) {
//
//                    List<SyncTask> list = produce();
//
//                    if(list != null && !list.isEmpty()) {
//                        pull(list);
//                    }
//
//                }
//                Thread.sleep(3L);
//            } catch (Exception ignored) {
//
//
//            }
//        }
//
//    }

}
