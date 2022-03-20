package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playGameButton=findViewById(R.id.btn_main_play_game);
        Button playStatsButton=findViewById(R.id.btn_main_stats);

        playGameButton.setOnClickListener(view -> playGame());
        playStatsButton.setOnClickListener(view -> checkStats());
    }

    public void playGame(){
        Intent summaryIntent= new Intent(MainActivity.this, GameActivity.class);
        startActivity(summaryIntent);
    }

    public void checkStats(){
        Intent statsIntent= new Intent(MainActivity.this, StatsActivity.class);
        startActivity(statsIntent);
    }
}