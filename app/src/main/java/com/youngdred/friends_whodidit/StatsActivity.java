package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        TextView firstPlayerName=findViewById(R.id.tv_stats_first_player_name);
        TextView firstPlayerPoints=findViewById(R.id.tv_stats_first_player_points);

        try {
            File path=getApplicationContext().getFilesDir();
            File fileToSave= new File(path, "stats.txt");

            Game gs1=getGameStats(fileToSave);
            firstPlayerName.setText(gs1.username);
            firstPlayerPoints.setText(String.valueOf(gs1.getPoints()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Game getGameStats(File file) throws IOException, ClassNotFoundException {
        Game result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Game) ois.readObject();
        }
        return result;
    }
}