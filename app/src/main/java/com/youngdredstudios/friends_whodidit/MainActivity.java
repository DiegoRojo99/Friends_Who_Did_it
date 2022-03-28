package com.youngdredstudios.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.youngdredstudios.friends_whodidit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playGameButton = findViewById(R.id.btn_main_play_game);
        Button checkLeaderboardButton = findViewById(R.id.btn_main_leaderboard);
        Button checkStatsButton = findViewById(R.id.btn_main_stats);
        Button achievementsButton = findViewById(R.id.btn_main_achievement);

        playGameButton.setOnClickListener(view -> playGame());
        checkLeaderboardButton.setOnClickListener(view -> checkLeaderboard());
        checkStatsButton.setOnClickListener(view -> checkStats());
        achievementsButton.setOnClickListener(view -> checkAchievements());
    }

    public void playGame(){
        Intent summaryIntent= new Intent(MainActivity.this, GameActivity.class);
        startActivity(summaryIntent);
    }

    public void checkLeaderboard(){
        Intent leaderboardIntent= new Intent(MainActivity.this, LeaderboardActivity.class);
        startActivity(leaderboardIntent);
    }

    public void checkStats(){
        Intent statsIntent= new Intent(MainActivity.this, StatsActivity.class);
        startActivity(statsIntent);
    }

    public void checkAchievements(){
        Intent achieveIntent= new Intent(MainActivity.this, AchievementActivity.class);
        startActivity(achieveIntent);
    }
}