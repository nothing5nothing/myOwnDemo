package com.jcx.military.project.multicast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateListener {
    Logger logger = LoggerFactory.getLogger(UserUpdateListener.class);

    @EventListener
    @Async
    public void onUserUpdate(UserUpdateEvent event) {
        System.out.println("Listening to user update event.");
        User user = event.getUser();
        logger.error(user.getName());
    }
}
