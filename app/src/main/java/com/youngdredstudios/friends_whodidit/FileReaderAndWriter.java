package com.youngdredstudios.friends_whodidit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;

public class FileReaderAndWriter {

    public void writeRecord(File fileName, int pointsRecord, int level) throws IOException {

        Writer output;
        output = new BufferedWriter(new FileWriter(fileName,true));
        output.append(level+":"+pointsRecord+"\n");
        output.close();

    }

    public int getNumberGamesPlayed(File fileName){
        Game[] allRecords=loadGames(fileName);
        return allRecords.length;
    }
    public int getBestRecord(File fileName){
        Game[] allRecords=loadGames(fileName);
        int record=0;

        for (int a=0; a<allRecords.length;a++){
            if(allRecords[a].points>record){
                record=allRecords[a].points;
            }
        }

        return record;
    }

    public int[] loadRecord(File fileName){
        int numberRecords=0;
        int[] allGameRecords = new int[0];

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    numberRecords++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;

                allGameRecords= new int[numberRecords];
                int index=0;
                while ((line = br.readLine()) != null) {
                    allGameRecords[index]=Integer.parseInt(line);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return allGameRecords;
    }


    public void clearFile(File fileName){

        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);

            Game clearGame=new Game(0,0);
            o.writeObject(clearGame);

            o.close();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeGame(File fileName, Game gameStats){
        Game[] leaders = new Game[5];
        leaders=this.loadGames(fileName);

        //Ordena los 5 mejores
        int correctStats=0;
        while (correctStats!=4){
            correctStats=0;
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
                for (int f=3;f>=a;f--){
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

        Game test= new Game(0,0);
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

