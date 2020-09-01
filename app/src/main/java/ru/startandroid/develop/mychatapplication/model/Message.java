package ru.startandroid.develop.mychatapplication.model;

public class Message {

    private String text;
    private String senderId;

    public Message(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
