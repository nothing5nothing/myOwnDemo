package com.jcx.military.project.multicast;


import org.springframework.context.ApplicationEvent;


public class UserUpdateEvent extends ApplicationEvent {

    private User user;
    public UserUpdateEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}