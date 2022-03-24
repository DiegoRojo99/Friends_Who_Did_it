package com.youngdredstudios.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        TextView firstPlayerLevel=findViewById(R.id.tv_leaderboard_first_player_level);
        TextView firstPlayerPoints=findViewById(R.id.tv_leaderboard_first_player_points);

        TextView secondPlayerLevel=findViewById(R.id.tv_leaderboard_second_player_name);
        TextView secondPlayerPoints=findViewById(R.id.tv_leaderboard_second_player_points);

        TextView thirdPlayerLevel=findViewById(R.id.tv_leaderboard_third_player_name);
        TextView thirdPlayerPoints=findViewById(R.id.tv_leaderboard_third_player_points);

        TextView fourthPlayerLevel=findViewById(R.id.tv_leaderboard_fourth_player_name);
        TextView fourthPlayerPoints=findViewById(R.id.tv_leaderboard_fourth_player_points);

        TextView fifthPlayerLevel=findViewById(R.id.tv_leaderboard_fifth_player_name);
        TextView fifthPlayerPoints=findViewById(R.id.tv_leaderboard_fifth_player_points);


        try {
            File path=getApplicationContext().getFilesDir();
            File fileToSave= new File(path, "stats.txt");

            Game[] ranking=getGameStats(fileToSave);

            firstPlayerLevel.setText(String.valueOf(ranking[0].level));
            firstPlayerPoints.setText(String.valueOf(ranking[0].getPoints()));

            secondPlayerLevel.setText(String.valueOf(ranking[1].level));
            secondPlayerPoints.setText(String.valueOf(ranking[1].getPoints()));

            thirdPlayerLevel.setText(String.valueOf(ranking[2].level));
            thirdPlayerPoints.setText(String.valueOf(ranking[2].getPoints()));

            fourthPlayerLevel.setText(String.valueOf(ranking[3].level));
            fourthPlayerPoints.setText(String.valueOf(ranking[3].getPoints()));

            fifthPlayerLevel.setText(String.valueOf(ranking[4].level));
            fifthPlayerPoints.setText(String.valueOf(ranking[4].getPoints()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Game[] getGameStats(File file){

        FileReaderAndWriter fraw=new FileReaderAndWriter();
        Game[] result = fraw.loadGames(file);

        return result;
    }
}