package com.jcx.military.project.task.registry.autoregistry;

import com.jcx.military.project.task.consumer.TaskConsumer;
import com.jcx.military.project.task.producer.Producer;
import com.jcx.military.project.task.registry.autoregistry.TaskRegister;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskAn;
import com.jcx.military.project.task.registry.autoregistry.annotation.TaskType;
import jakarta.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TaskRegisterPostProcess implements BeanPostProcessor, EnvironmentAware {
    private Environment environment;

    public TaskRegisterPostProcess() {
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        TaskRegister.consumerPool = new ThreadPoolExecutor(Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.consumer.core")).orElse("40")), Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.consumer.max")).orElse("100")), 2L, TimeUnit.SECONDS, new ArrayBlockingQueue(Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.consumer.capacity")).orElse("100"))));
        TaskRegister.producerPool = new ThreadPoolExecutor(Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.producer.core")).orElse("4")), Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.producer.max")).orElse("20")), 2L, TimeUnit.SECONDS, new ArrayBlockingQueue(Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.producer.capacity")).orElse("100"))));
        int retry = Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.batch.retry")).orElse("40"));
        int download = Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.batch.download")).orElse("40"));
        int pack = Integer.parseInt((String)Optional.ofNullable(this.environment.getProperty("task.batch.pack")).orElse("40"));
        TaskRegister.queue.put(TaskType.RETRY, new LinkedBlockingQueue(retry * 2));
        TaskRegister.queue.put(TaskType.Download, new LinkedBlockingQueue(download * 2));
        TaskRegister.queue.put(TaskType.PACK, new LinkedBlockingQueue(pack * 2));
        TaskRegister.batch.put(TaskType.RETRY, retry);
        TaskRegister.batch.put(TaskType.PACK, pack);
        TaskRegister.batch.put(TaskType.Download, download);
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Annotation[] annotations = bean.getClass().getAnnotations();
        Annotation[] var4 = annotations;
        int var5 = annotations.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Annotation a = var4[var6];
            if (a instanceof TaskAn) {
                TaskType type = ((TaskAn)a).type();
                if (bean instanceof TaskConsumer) {
                    TaskConsumer<?> consumer = (TaskConsumer)bean;
                    TaskRegister.consumerMap.put(type, consumer);
                    TaskRegister.phoreConsumerMap.put(type, new Semaphore(((TaskAn)a).count()));
                    break;
                }

                if (bean instanceof Producer) {
                    Producer<?> producer = (Producer)bean;
                    TaskRegister.producerMap.put(type, producer);
                    TaskRegister.phoreProducerMap.put(type, new Semaphore(((TaskAn)a).count()));
                    break;
                }
            }
        }

        return bean;
    }
}