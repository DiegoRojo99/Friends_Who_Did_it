package com.youngdred.friends_whodidit;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {

    private static final long serialVersionUID = 8736847634070552888L;

    String username;
    int points;

    public Game(String username, int points) {
        this.username = username;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public void saveGameStats(File fts){

        try (FileOutputStream fos = new FileOutputStream(fts);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
