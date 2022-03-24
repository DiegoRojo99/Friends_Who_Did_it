package com.youngdredstudios.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.youngdredstudios.friends_whodidit.R;
import java.io.File;

public class FinishedGameSummary extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game_summary);

        updateStats();

        Button mainMenuButton = findViewById(R.id.btn_summary_main_menu);
        Button newGameButton = findViewById(R.id.btn_summary_new_game);
        mainMenuButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);

    }

    public void sendToMenu(){
        Intent menuIntent= new Intent(FinishedGameSummary.this, MainActivity.class);
        startActivity(menuIntent);
    }

    public void newGame(){
        Intent gameIntent= new Intent(FinishedGameSummary.this, GameActivity.class);
        startActivity(gameIntent);
    }

    public void updateStats(){

        Intent mainIntent=getIntent();
        String level=mainIntent.getStringExtra("Level");
        String finalScore=mainIntent.getStringExtra("Final Score");

        TextView finalScoreNumber = findViewById(R.id.tv_summary_final_score_number);
        TextView levelNumber = findViewById(R.id.tv_summary_level_number);

        finalScoreNumber.setText(finalScore);
        levelNumber.setText(level);

    }

    public void saveStats(){
        Intent mainIntent=getIntent();
        String correctAnswers=mainIntent.getStringExtra("Correct Answers");
        String totalAnswers=mainIntent.getStringExtra("Total Answers");

        String username= "Young Dred";
        int points=10*(Integer.parseInt(correctAnswers));
        Game gameStats = new Game(username,points);

        File path=getApplicationContext().getFilesDir();
        File fileToSave= new File(path, "stats.txt");

        FileReaderAndWriter fr= new FileReaderAndWriter();
        fr.writeGame(fileToSave, gameStats);

        Intent sendToStats= new Intent(FinishedGameSummary.this, LeaderboardActivity.class);
        startActivity(sendToStats);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_summary_main_menu){
            sendToMenu();
        } else if(view.getId()==R.id.btn_summary_new_game){
            newGame();
        }
    }
}