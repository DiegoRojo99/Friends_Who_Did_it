package com.youngdredstudios.friends_whodidit;

public class UserAchievement {

    String achievementId;
    int actualProgress;
    boolean completed;
    String userId;

    public UserAchievement(String achievementId, int actualProgress,boolean completed, String userId) {
        this.achievementId = achievementId;
        this.actualProgress = actualProgress;
        this.completed= completed;
        this.userId=userId;
    }
}
