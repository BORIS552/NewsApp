package com.example.boris.worldexplorer.model.transaction;

/**
 * Created by boris on 11/6/17.
 */

public class User {
    String name;
    String userId;
    public User(String name,String userId){
        this.name = name;
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

}
