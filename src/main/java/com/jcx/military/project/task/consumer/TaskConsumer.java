package com.jcx.military.project.task.consumer;

public interface TaskConsumer<E>{

    /**
     * 获取一个任务
     * @return
     */
    void consumer(E e);

}
