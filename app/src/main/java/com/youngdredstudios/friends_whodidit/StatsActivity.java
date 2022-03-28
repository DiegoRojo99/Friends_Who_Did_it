package com.youngdredstudios.friends_whodidit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.youngdredstudios.friends_whodidit.R;

import java.io.File;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView recordHolderTV, numberTimesPlayedTV;
    private Button resetStatsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        updateTVs();

        resetStatsButton=(Button) findViewById(R.id.btn_stats_reset_stats);
        resetStatsButton.setOnClickListener(this);

    }
    public void updateTVs(){


    }

    public void resetStats(){

    }

    @Override
    public void onClick(View view) {
        if(view==resetStatsButton){
            resetStats();
        }
    }
}