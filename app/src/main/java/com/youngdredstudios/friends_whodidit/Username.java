package com.youngdredstudios.friends_whodidit;

public class Username {
    String userID;
    String username;

    public Username(String uid, String name){
        this.username=name;
        this.userID=uid;
    }

    @Override
    public String toString() {
        return "Username{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
