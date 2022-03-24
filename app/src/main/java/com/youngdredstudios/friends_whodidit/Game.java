package com.youngdredstudios.friends_whodidit;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private static final long serialVersionUID = 8736847634070552888L;

    int level;
    int points;

    public Game(int level, int points) {
        this.level = level;
        this.points = points;
    }


    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = Game.this.level;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Game{" +
                "level=" + level +
                ", points=" + points +
                '}';
    }

}
