package com.jcx.military.project.task.producer;

import java.util.List;

public interface Producer<E> {


    // 数据库中拉取文件
    List<E> produce(int batch);

//    // 把文件推到中间队列中
//    void pull(List<E> e);

}
