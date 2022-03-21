package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import com.youngdred.friends_whodidit.Game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

        EditText usernameEditText=findViewById(R.id.et_summary_username);
        Button saveStatsButton = findViewById(R.id.button_summary_save_stats);

        correctAnswerTextView.setText(correctAnswers);
        totalAnswerTextView.setText(String.valueOf(totalAnswers));

        TextView pointsTest=findViewById(R.id.tv_summary_points_test);

        String username= String.valueOf(usernameEditText.getText());
        int points=10*(Integer.parseInt(correctAnswers));

        Game gameStats = new Game(username,points);

        saveStatsButton.setOnClickListener(
                view -> {
                    pointsTest.setText(String.valueOf(2000));



                    try {
                        File path=getApplicationContext().getFilesDir();
                        File fileToSave= new File(path, "stats.txt");
                        gameStats.saveGameStats(fileToSave);

                        Game gm1=getGameStats(fileToSave);
                        pointsTest.setText(String.valueOf(gm1.getPoints()));

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

        );

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