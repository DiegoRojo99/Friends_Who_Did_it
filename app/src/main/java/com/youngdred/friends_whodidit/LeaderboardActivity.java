package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        TextView firstPlayerName=findViewById(R.id.tv_stats_first_player_name);
        TextView firstPlayerPoints=findViewById(R.id.tv_stats_first_player_points);

        TextView secondPlayerName=findViewById(R.id.tv_stats_second_player_name);
        TextView secondPlayerPoints=findViewById(R.id.tv_stats_second_player_points);

        TextView thirdPlayerName=findViewById(R.id.tv_stats_third_player_name);
        TextView thirdPlayerPoints=findViewById(R.id.tv_stats_third_player_points);

        TextView fourthPlayerName=findViewById(R.id.tv_stats_fourth_player_name);
        TextView fourthPlayerPoints=findViewById(R.id.tv_stats_fourth_player_points);

        TextView fifthPlayerName=findViewById(R.id.tv_stats_fifth_player_name);
        TextView fifthPlayerPoints=findViewById(R.id.tv_stats_fifth_player_points);


        try {
            File path=getApplicationContext().getFilesDir();
            File fileToSave= new File(path, "stats.txt");

            Game[] ranking=getGameStats(fileToSave);

            firstPlayerName.setText(ranking[0].username);
            firstPlayerPoints.setText(String.valueOf(ranking[0].getPoints()));

            secondPlayerName.setText(ranking[1].username);
            secondPlayerPoints.setText(String.valueOf(ranking[1].getPoints()));

            thirdPlayerName.setText(ranking[2].username);
            thirdPlayerPoints.setText(String.valueOf(ranking[2].getPoints()));

            fourthPlayerName.setText(ranking[3].username);
            fourthPlayerPoints.setText(String.valueOf(ranking[3].getPoints()));

            fifthPlayerName.setText(ranking[4].username);
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