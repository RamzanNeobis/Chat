package ru.startandroid.develop.mychatapplication.model;

import java.io.Serializable;
import java.util.List;

public class Chat implements Serializable {



    private String id;
    private List<String> userId;


    public Chat(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }



}
