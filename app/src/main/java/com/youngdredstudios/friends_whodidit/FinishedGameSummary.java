package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.youngdredstudios.friends_whodidit.R;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FinishedGameSummary extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game_summary);

        updateStats();
        addGameToDB();

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

    public void addGameToDB(){

        Intent mainIntent=getIntent();
        String totalPoints=mainIntent.getStringExtra("Final Score");
        String levelString=mainIntent.getStringExtra("Level");

        int points=(Integer.parseInt(totalPoints));
        int level=(Integer.parseInt(levelString));

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        Map<String,Object> game=new HashMap<>();
        game.put("level",level);
        game.put("points",points);
        game.put("user",user.getUid());

        db.collection("games")
                .add(game)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

/*
    public void saveGameStats(){
        Intent mainIntent=getIntent();
        String totalPoints=mainIntent.getStringExtra("Final Score");
        String levelString=mainIntent.getStringExtra("Level");

        int points=(Integer.parseInt(totalPoints));
        int level=(Integer.parseInt(levelString));

        File path=getApplicationContext().getFilesDir();
        File fileToSave= new File(path, "gameRecords.txt");

        FileReaderAndWriter fr= new FileReaderAndWriter();
        fr.writeGame(fileToSave, gameStats);

        Intent sendToStats= new Intent(FinishedGameSummary.this, LeaderboardActivity.class);
        startActivity(sendToStats);
    }
*/

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_summary_main_menu){
            sendToMenu();
        } else if(view.getId()==R.id.btn_summary_new_game){
            newGame();
        }
    }
}