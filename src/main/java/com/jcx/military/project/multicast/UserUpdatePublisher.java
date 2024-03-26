package com.jcx.military.project.multicast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserUpdatePublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(User user) {
        System.out.println("Publishing user update event.");
        UserUpdateEvent userUpdateEvent = new UserUpdateEvent(user);
        applicationEventPublisher.publishEvent(userUpdateEvent);
    }
}
