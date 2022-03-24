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

        try {
            FileReaderAndWriter fr=new FileReaderAndWriter();
            File path=getApplicationContext().getFilesDir();
            File fileToUse= new File(path, "stats.txt");
            String record=String.valueOf(fr.getBestRecord(fileToUse));
            String numberTimes=String.valueOf(fr.getNumberGamesPlayed(fileToUse));

            if(!record.isEmpty()){
                recordHolderTV=(TextView) findViewById(R.id.tv_stats_highest_record_info);
                recordHolderTV.setText(record);
            }
            if(!numberTimes.isEmpty()){
                numberTimesPlayedTV=(TextView) findViewById(R.id.tv_stats_number_times_info);
                numberTimesPlayedTV.setText(numberTimes);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void resetStats(){
        FileReaderAndWriter fr=new FileReaderAndWriter();
        File path=getApplicationContext().getFilesDir();
        File fileToClear= new File(path, "stats.txt");
        fr.clearFile(fileToClear);

        startActivity(new Intent(StatsActivity.this,MainActivity.class));

    }

    @Override
    public void onClick(View view) {
        if(view==resetStatsButton){
            resetStats();
        }
    }
}