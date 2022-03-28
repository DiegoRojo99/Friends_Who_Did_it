package com.youngdredstudios.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.youngdredstudios.friends_whodidit.R;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;

    Button playGameButton , checkLeaderboardButton, checkStatsButton;
    Button achievementsButton, loginButton, signoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        playGameButton = findViewById(R.id.btn_main_play_game);
        checkLeaderboardButton = findViewById(R.id.btn_main_leaderboard);
        checkStatsButton = findViewById(R.id.btn_main_stats);
        achievementsButton = findViewById(R.id.btn_main_achievement);
        loginButton = findViewById(R.id.btn_main_login);
        signoutButton = findViewById(R.id.btn_main_signout);

        playGameButton.setOnClickListener(view -> playGame());
        checkLeaderboardButton.setOnClickListener(view -> checkLeaderboard());
        checkStatsButton.setOnClickListener(view -> checkStats());
        achievementsButton.setOnClickListener(view -> checkAchievements());
        loginButton.setOnClickListener(view -> goToLogin());
        signoutButton.setOnClickListener(view -> signOut());

        //checkUserLoggedIn();
    }

    public void checkUserLoggedIn(){
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            user=FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null){
                if (user.isEmailVerified()){
                    loginButton.setVisibility(View.GONE);
                    signoutButton.setVisibility(View.VISIBLE);
                }else{
                    achievementsButton.setVisibility(View.GONE);
                }
            }else{
            }
        }


    }

    public void signOut(){
        mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(MainActivity.this,MainActivity.class));
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
        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            if (user.isEmailVerified()){
                Intent achieveIntent= new Intent(MainActivity.this, AchievementActivity.class);
                startActivity(achieveIntent);
            }else{
                Toast.makeText(MainActivity.this, "You have to be logged in to check your achievements",Toast.LENGTH_LONG).show();
            }
        }else{
            //goToLogin();
        }
    }

    public void goToLogin(){
        Intent loginIntent= new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}