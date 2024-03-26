package com.jcx.military.project.task.registry.autoregistry;

import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import com.jcx.military.project.task.consumer.TaskConsumer;
import com.jcx.military.project.task.producer.Producer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;


public class TaskRegister {

    public static final Map<TaskType, TaskConsumer> consumerMap = new HashMap();

    public static final Map<TaskType, Producer<?>> producerMap = new HashMap();

    public static final Map<TaskType, Semaphore> phoreConsumerMap = new HashMap();

    public static final Map<TaskType, Semaphore> phoreProducerMap = new HashMap();

    public static final Map<TaskType, LinkedBlockingQueue> queue = new HashMap();

    public static final Map<TaskType, Integer> batch = new HashMap();

    public static ThreadPoolExecutor consumerPool ;

    public static ThreadPoolExecutor producerPool ;

    public static ThreadPoolExecutor getConsumerPool() {
        return consumerPool;
    }

    public static void setConsumerPool(ThreadPoolExecutor consumerPool) {
        TaskRegister.consumerPool = consumerPool;
    }

    public static ThreadPoolExecutor getProducerPool() {
        return producerPool;
    }

    public static void setProducerPool(ThreadPoolExecutor producerPool) {
        TaskRegister.producerPool = producerPool;
    }
}
