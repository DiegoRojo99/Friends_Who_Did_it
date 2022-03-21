package com.youngdred.friends_whodidit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileReaderAndWriter {

    public void writeGame(File fileName, Game gameStats){
        Game[] leaders = new Game[5];
        leaders=this.loadGames(fileName);

        //Ordena los 5 mejores
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

        for (int a=0;a<leaders.length;a++){
            if(gameStats.points>leaders[a].points){
                for (int f=3;f>a;f--){
                    leaders[f+1]=leaders[f];
                }
                leaders[a]=gameStats;
                break;
            }
        }

        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);

            for (int index=0;index<leaders.length;index++){
                o.writeObject(leaders[index]);
            }

            o.close();f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game[] loadGames(File fileName){

        Game test= new Game("",0);
        Game[] ranking={test,test,test,test,test};
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            for (int i=0;i<5;i++){
                Game gs=(Game) oi.readObject();
                ranking[i]=gs;
            }

            oi.close();fi.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ranking;
    }

}

