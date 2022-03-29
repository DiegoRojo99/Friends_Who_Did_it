package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        Game g=new Game(user.getUid(),Integer.parseInt(level),Integer.parseInt(finalScore));
        checkAchievements(g);

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

    public void checkAchievements(Game g){
        checkFirstAchievement(g);
        checkSecondAchievement(g);
        checkThirdAchievement(g);
    }

    public void updateFirstAchievement(UserAchievement userAchievement, Game game, String docId){
        if(game.points>=1000){
            db.collection("userAchievements")
                    .document(docId)
                    .update("Completed",true);
            db.collection("userAchievements")
                    .document(docId)
                    .update("ActualProgress",1000);
        }else if(game.points>userAchievement.actualProgress){
            db.collection("userAchievements")
                    .document(docId)
                    .update("ActualProgress",game.points);
        }
    }

    public void checkFirstAchievement(Game g){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("userAchievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String achievementID=String.valueOf(document.get("AchievementID"));
                                String userId=document.getString("UserId");
                                int actual=Integer.parseInt(String.valueOf(document.get("ActualProgress")));
                                boolean completed=Boolean.parseBoolean(String.valueOf(document.get("Completed")));
                                UserAchievement userAchievement=new UserAchievement(achievementID,actual,completed,userId);
                                if (firebaseUser != null && userId.equals(firebaseUser.getUid()) && achievementID.equals("0")) {
                                    String docId=document.getId();
                                    updateFirstAchievement(userAchievement, g, docId);
                                }
                            }
                        }
                    }
                });
    }

    public void updateSecondAchievement(UserAchievement userAchievement, Game game, String docId){
        if(game.level>=5){
            db.collection("userAchievements")
                    .document(docId)
                    .update("Completed",true);
            db.collection("userAchievements")
                    .document(docId)
                    .update("ActualProgress",5);
        }else if(game.level>userAchievement.actualProgress){
            db.collection("userAchievements")
                    .document(docId)
                    .update("ActualProgress",game.level);
        }
    }

    public void checkSecondAchievement(Game g){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("userAchievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String achievementID=String.valueOf(document.get("AchievementID"));
                                String userId=document.getString("UserId");
                                int actual=Integer.parseInt(String.valueOf(document.get("ActualProgress")));
                                boolean completed=Boolean.parseBoolean(String.valueOf(document.get("Completed")));
                                UserAchievement userAchievement=new UserAchievement(achievementID,actual,completed,userId);
                                if (firebaseUser != null && userId.equals(firebaseUser.getUid()) && achievementID.equals("1")) {
                                    String docId=document.getId();
                                    updateSecondAchievement(userAchievement, g, docId);
                                }
                            }
                        }
                    }
                });
    }

    public void updateThirdAchievement(UserAchievement userAchievement, Game game, String docId){
        if(game.level>=3){
            db.collection("userAchievements")
                    .document(docId)
                    .update("Completed",true);
            db.collection("userAchievements")
                    .document(docId)
                    .update("ActualProgress",3);
        }else if(game.level>userAchievement.actualProgress){
            db.collection("userAchievements")
                    .document(docId)
                    .update("ActualProgress",game.level);
        }
    }

    public void checkThirdAchievement(Game g){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("userAchievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String achievementID=String.valueOf(document.get("AchievementID"));
                                String userId=document.getString("UserId");
                                int actual=Integer.parseInt(String.valueOf(document.get("ActualProgress")));
                                boolean completed=Boolean.parseBoolean(String.valueOf(document.get("Completed")));
                                UserAchievement userAchievement=new UserAchievement(achievementID,actual,completed,userId);
                                if (firebaseUser != null && userId.equals(firebaseUser.getUid()) && achievementID.equals("2")) {
                                    String docId=document.getId();
                                    updateThirdAchievement(userAchievement, g, docId);
                                }
                            }
                        }
                    }
                });
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