package com.jcx.military.project.multicast;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateListener2 implements ApplicationListener<UserUpdateEvent> {
    @Override
    public void onApplicationEvent(UserUpdateEvent event) {

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
