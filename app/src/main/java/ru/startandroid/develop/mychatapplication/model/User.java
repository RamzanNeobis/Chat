package ru.startandroid.develop.mychatapplication.model;

import java.io.Serializable;

public class User  implements Serializable {


    private String id;
    private String name;

    public User(){

    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}
