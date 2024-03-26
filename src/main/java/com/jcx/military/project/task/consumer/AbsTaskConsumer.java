package com.jcx.military.project.task.consumer;

import java.util.Random;

public class AbsTaskConsumer<E> implements TaskConsumer<E> {
    public AbsTaskConsumer() {
    }

    public void sleep() {
        try {
            Random random = new Random();
            Thread.sleep((long)random.nextInt(5));
        } catch (Exception var2) {
        }

    }

    public void consumer(E e) {
    }
}
