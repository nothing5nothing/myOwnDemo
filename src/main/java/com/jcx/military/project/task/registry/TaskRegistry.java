package com.jcx.military.project.task.registry;


import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.concurrent.*;


public abstract class TaskRegistry<T> {

    /**
     * 信号量去控制 多个comsumer从队列中取数据
     */
    Semaphore producerSemaphore;

    Semaphore consumerSemaphore;

    LinkedBlockingQueue<T> linkedBlockingQueue;

    int batch;

    public ThreadPoolExecutor pool ;

    @PostConstruct
    public void init() {
    }

    public TaskRegistry(int cSphoreCount, LinkedBlockingQueue<T> linkedBlockingQueue, int batch, ThreadPoolExecutor pool) {
        this.producerSemaphore = new Semaphore(1);
        this.consumerSemaphore = new Semaphore(cSphoreCount);
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.batch = batch;
        this.pool = pool;
    }


    public void producerHandler() {
        pool.submit(() -> {
            pull(getProducer());
            sleep();
            producerSemaphore.release(1);
        });
    }

    public void consumerHandler() {
        pool.submit(() -> {
            getCosumer(linkedBlockingQueue.poll());
            consumerSemaphore.release(1);
        });
    }

    public void sleep() {
        try{
            Thread.sleep(5);
        } catch (InterruptedException ignored) {

        }
    }

    public void pull(List<T> list) {
        if(list == null || list.isEmpty()){
            return;
        }
        for(T t : list) {
           try{
               linkedBlockingQueue.put(t);
           }catch (InterruptedException ignored) {

           }
        }
    }

    abstract List<T> getProducer();


    abstract void getCosumer(T t);


    public Semaphore getProducerSemaphore() {
        return producerSemaphore;
    }

    public void setProducerSemaphore(Semaphore producerSemaphore) {
        this.producerSemaphore = producerSemaphore;
    }

    public Semaphore getConsumerSemaphore() {
        return consumerSemaphore;
    }

    public void setConsumerSemaphore(Semaphore consumerSemaphore) {
        this.consumerSemaphore = consumerSemaphore;
    }

    public LinkedBlockingQueue<T> getLinkedBlockingQueue() {
        return linkedBlockingQueue;
    }

    public void setLinkedBlockingQueue(LinkedBlockingQueue<T> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
}
