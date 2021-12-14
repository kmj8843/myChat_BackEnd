package com.rlalsa8843.mychatbe.fcm.model;

public class Message {
    private Notification notification;
    private String token;

    public Message(Notification notification, String token) {
        this.notification = notification;
        this.token = token;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
