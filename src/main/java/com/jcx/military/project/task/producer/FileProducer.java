package com.jcx.military.project.task.producer;


import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import com.jcx.military.project.task.registry.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public abstract class FileProducer implements Producer<SyncTaskFile>{

    Logger log = LoggerFactory.getLogger(FileProducer.class);

//    int batch;

    int fileStatus;

//    LinkedBlockingQueue<SyncTaskFile> LinkedBlockingQueue;

    @Autowired
    TaskService taskService;

//    public FileProducer(int batch, LinkedBlockingQueue<SyncTaskFile> list, int fileStatus) {
//        this.batch = batch;
//        this.LinkedBlockingQueue = list;
//        this.fileStatus = fileStatus;
//    }


    public FileProducer(int fileStatus) {
        this.fileStatus = fileStatus;
    }

    @Override
    public List<SyncTaskFile> produce(int batch) {
        List<SyncTaskFile> list =  taskService.selectTaskFile(fileStatus, batch);
        log.error(list == null ? "生产 null": "生产：" + list.size());
        return list;
    }

//    @Override
//    public void pull(List<SyncTaskFile> syncTaskFile) {
//        syncTaskFile.forEach(file -> {
//            try {
//                LinkedBlockingQueue.put(file);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }

//    @Override
//    public void run() {
//        while(true) {
//            try{
//                if (LinkedBlockingQueue.size() < batch) {
//
//                    List<SyncTaskFile> list = produce();
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
