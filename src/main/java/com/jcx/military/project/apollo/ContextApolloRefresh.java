//package com.jcx.military.project.apollo;
//
//import com.ctrip.framework.apollo.model.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.cloud.context.scope.refresh.RefreshScope;
//import org.springframework.stereotype.Component;
//
//@Component
///**
// * 热发布监听
// */
//public class ContextApolloRefresh {
//    /**
//     * apollo配置更新时，刷新applicationcontext容器
//     */
//    private final RefreshScope refreshScope;
//    /**
//     * 初始化刷新
//     * @param refreshScope
//     */
//    public ContextApolloRefresh(RefreshScope refreshScope) {
//        this.refreshScope = refreshScope;
//    }
//    /**
//     * apollo配置监听
//     * @param changeEvent
//     */
//    @ApolloConfigChangeListener(value = {"application", "TEST1.apollo"},interestedKeyPrefixes = {"net.cnki.demo"})
//    public void onChange(ConfigChangeEvent changeEvent) {
//        /**
//         * 这是可以打印出刷新前读取配置
//         */
//
//        refreshScope.refreshAll();
//        /**
//         * 这里可以打印刷新后读取的配置
//         */
//    }
//}