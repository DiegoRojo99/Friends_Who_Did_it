package com.youngdredstudios.friends_whodidit;

public class UserAchievement {

    String achievementId;
    int actualProgress;
    int totalProgress;
    boolean completed;

    public UserAchievement(String achievementId, int actualProgress, int totalProgress) {
        this.achievementId = achievementId;
        this.actualProgress = actualProgress;
        this.totalProgress = totalProgress;
        this.completed= actualProgress == totalProgress;
    }
}
