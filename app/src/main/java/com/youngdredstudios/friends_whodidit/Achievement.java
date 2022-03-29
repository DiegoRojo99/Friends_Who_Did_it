package com.youngdredstudios.friends_whodidit;

public class Achievement {

    String achievementId;
    String title;
    String desc;
    int totalProgress;

    public Achievement(String desc, int totalProgress,String achievementId, String title) {
        this.achievementId = achievementId;
        this.title=title;
        this.desc=desc;
        this.totalProgress=totalProgress;
    }
}
