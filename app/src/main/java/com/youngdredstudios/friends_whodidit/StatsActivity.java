package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.youngdredstudios.friends_whodidit.R;

import java.io.File;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView recordHolderTV, numberTimesPlayedTV;
    private Button resetStatsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        getUserStats();

        recordHolderTV=findViewById(R.id.tv_stats_highest_record_info);
        numberTimesPlayedTV=findViewById(R.id.tv_stats_number_times_info);

        resetStatsButton=(Button) findViewById(R.id.btn_stats_reset_stats);
        resetStatsButton.setOnClickListener(this);

    }

    public void updateNumberTimes(int n){
        numberTimesPlayedTV.setText(String.valueOf(n));
    }
    public void updateRecord(int n){
        recordHolderTV.setText(String.valueOf(n));
    }
    public void getUserStats(){
        final int[] nt = {0};
        final int[] record = {0};

        FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();
        String uid=usuario.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("games")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] valores=document.getData().values().toArray();
                                if(valores[1].equals(uid)){
                                    nt[0] = nt[0] + 1;
                                    int actGamePoints=Integer.parseInt(String.valueOf(valores[2]));
                                    int maxGamePoints=Integer.parseInt(String.valueOf(record[0]));
                                    if(actGamePoints>maxGamePoints){
                                        record[0]=actGamePoints;
                                    }
                                }
                            }
                            updateNumberTimes(nt[0]);
                            updateRecord(record[0]);
                        }
                    }
                });
    }
    public void goToMain(){
        Intent mainIntent=new Intent(StatsActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
    public void showAlertDialog(){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle("CONFIRM RESET");
            alert.setMessage("Are you sure you want to reset your stats. All of your game stats will disappear");
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetStats();
                goToMain();
            }
        });
        alert.create().show();
    }
    public void deleteStats(String dID){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("games").document(dID)
                .delete();

    }

    public void resetStats(){


        FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();
        String uid=usuario.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("games")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] valores=document.getData().values().toArray();
                                if(valores[1].equals(uid)){
                                    deleteStats(document.getId());
                                }
                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view==resetStatsButton){
            showAlertDialog();
        }
    }
}