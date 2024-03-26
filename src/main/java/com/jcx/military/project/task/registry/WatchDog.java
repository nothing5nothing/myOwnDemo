package com.jcx.military.project.task.registry;


import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WatchDog implements Runnable{


    @Autowired
    List<TaskRegistry> list;


    @Override
    public void run() {
        while (true) {
            for(TaskRegistry registry : list) {
                // 生产者可以生产任务
                if ( registry.getProducerSemaphore().tryAcquire(1) ) {
                    if ( registry.getLinkedBlockingQueue().size() < registry.batch ) {
                        registry.producerHandler();
                    } else {
                        registry.getProducerSemaphore().release(1);
                    }
                }
                if( registry.getConsumerSemaphore().tryAcquire(1) ){
                    if( !registry.getLinkedBlockingQueue().isEmpty() ) {
                        registry.consumerHandler();
                    } else {
                        registry.getConsumerSemaphore().release(1);
                    }
                }


            }
        }
    }
}
