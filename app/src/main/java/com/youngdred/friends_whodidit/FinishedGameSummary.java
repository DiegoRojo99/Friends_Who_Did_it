package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

        correctAnswerTextView.setText(correctAnswers);
        totalAnswerTextView.setText(String.valueOf(totalAnswers));


    }
}