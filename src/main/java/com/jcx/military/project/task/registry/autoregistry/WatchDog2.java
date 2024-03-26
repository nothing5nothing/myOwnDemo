package com.jcx.military.project.task.registry.autoregistry;

import com.jcx.military.project.task.producer.FileProducer;
import com.jcx.military.project.task.registry.TaskRegistry;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class WatchDog2 implements Runnable{
    Logger log = LoggerFactory.getLogger(WatchDog2.class);
    public void run() {
        while (true) {

            for(TaskType type : TaskType.values()) {
                log.error(type.toString() + "当前队列个数："+TaskRegister.queue.get(type).size());
                int batch = TaskRegister.batch.get(type);

                if ( TaskRegister.phoreProducerMap.get(type).tryAcquire(1) ) {
                    if ( TaskRegister.queue.get(type).size() < batch) {
                        product(type, batch);
                    } else {
                        TaskRegister.phoreProducerMap.get(type).release(1);
                    }
                }
                if( TaskRegister.phoreConsumerMap.get(type).tryAcquire(1) ){
                    if( !TaskRegister.queue.get(type).isEmpty() ) {
                        consumer(type);
                    } else {
                        TaskRegister.phoreConsumerMap.get(type).release(1);
                    }
                }

            }

        }
    }

    public void product(TaskType type, int batch) {

        TaskRegister.producerPool.submit( ()->{
            try {
                List list = TaskRegister.producerMap.get(type).produce(batch);
                if(list == null) {
                    return;
                }
                for(Object l : list) {
                    TaskRegister.queue.get(type).put(l);
                }
                sleep();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                TaskRegister.phoreProducerMap.get(type).release(1);
            }

        } );
    }

    public void consumer(TaskType type) {

        TaskRegister.consumerPool.execute( ()->{
            try {
                TaskRegister.consumerMap.get(type).consumer(
                        TaskRegister.queue.get(type).poll());
                sleep();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                TaskRegister.phoreConsumerMap.get(type).release(1);
            }

        } );
    }



    public void sleep() {
        try{
            Thread.sleep(5);
        } catch (InterruptedException ignored) {

        }
    }
}
