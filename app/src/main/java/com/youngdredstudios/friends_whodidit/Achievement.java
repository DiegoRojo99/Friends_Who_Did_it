package com.youngdredstudios.friends_whodidit;

public class Achievement {

    String title;
    String desc;
    int actualProgress;
    int totalProgress;
    boolean completed;

    public Achievement(String title, String desc, int actualProgress, int totalProgress) {
        this.title = title;
        this.desc = desc;
        this.actualProgress = actualProgress;
        this.totalProgress = totalProgress;
        this.completed= actualProgress == totalProgress;
    }
}
