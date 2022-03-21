package com.youngdred.friends_whodidit;

import com.youngdred.friends_whodidit.Game;

import java.io.File;

public class Leaderboard {
    public Game[] leaders= new Game[5];

    public Leaderboard(Game[] ranking) {
        this.leaders = ranking;
    }

    public void orderBoard(){
        int correctStats=0;

        while (correctStats!=4){
            for (int i=0; i<4;i++){
                if(leaders[i].points>=leaders[i+1].points){
                    correctStats++;
                }else{
                    Game lowerGame=leaders[i];
                    leaders[i]=leaders[i+1];
                    leaders[i+1]=lowerGame;
                }
            }
        }
    }

}
