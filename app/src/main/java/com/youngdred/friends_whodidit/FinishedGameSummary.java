package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class FinishedGameSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game_summary);

        Intent mainIntent=getIntent();
        String correctAnswers=mainIntent.getStringExtra("Correct Answers");
        String totalAnswers=mainIntent.getStringExtra("Total Answers");

        TextView correctAnswerTextView = findViewById(R.id.tv_summary_correct_answers);
        TextView totalAnswerTextView = findViewById(R.id.tv_summary_total_answers);

        EditText usernameEditText=(EditText)findViewById(R.id.et_summary_username);
        usernameEditText.setText("");
        Button saveStatsButton = findViewById(R.id.button_summary_save_stats);

        correctAnswerTextView.setText(correctAnswers);
        totalAnswerTextView.setText(String.valueOf(totalAnswers));

        saveStatsButton.setOnClickListener(
                view -> {

                    String username= String.valueOf(usernameEditText.getText().toString());
                    int points=10*(Integer.parseInt(correctAnswers));
                    Game gameStats = new Game(username,points);

                    File path=getApplicationContext().getFilesDir();
                    File fileToSave= new File(path, "stats.txt");

                    FileReaderAndWriter fr= new FileReaderAndWriter();
                    fr.writeGame(fileToSave, gameStats);

                    Intent sendToStats= new Intent(FinishedGameSummary.this, LeaderboardActivity.class);
                    startActivity(sendToStats);

                }

        );

    }



}