package com.youngdred.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NextLevelActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);

        getLevelAndPoints();

        Button continueButton=findViewById(R.id.btn_next_level_continue);
        continueButton.setOnClickListener(this);


    }

    public void getLevelAndPoints(){
        Intent gameIntent=getIntent();

        String level=gameIntent.getStringExtra("Level");
        String points=gameIntent.getStringExtra("Points");

        int levelNext=Integer.parseInt(level)+1;
    }


    public void onClick(View view) {
        if(view.getId()==R.id.btn_next_level_continue){
            Intent gameIntent=getIntent();

            String level=gameIntent.getStringExtra("Level");
            String points=gameIntent.getStringExtra("Points");
            int levelNext=Integer.parseInt(level)+1;
            String levelNextString=String.valueOf(levelNext);

            sendToMainAgain(points,levelNextString);
        }
    }

    public void sendToMainAgain(String p, String l){

        Intent sendMainIntent=new Intent(NextLevelActivity.this, GameActivity.class);
        sendMainIntent.putExtra("Points",p);
        sendMainIntent.putExtra("Level",String.valueOf(l));
        startActivity(sendMainIntent);
    }
}