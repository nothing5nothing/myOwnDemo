//package com.jcx.military.project.task.registry.autoregistry;
//
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class TaskRegisterConfigReistar implements BeanDefinitionRegistryPostProcessor {
//
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(
//                TaskRegisterPostProcess.class).getBeanDefinition();
//        registry.registerBeanDefinition("taskRegisterPostProcess",beanDefinition);
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        BeanDefinitionRegistryPostProcessor.super.postProcessBeanFactory(beanFactory);
//    }
//}
