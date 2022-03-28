package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LeaderboardActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ProgressBar pbLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        pbLeaderboard=(ProgressBar)findViewById(R.id.progress_bar_leaderboard);

        updateLeaderboard();

    }

    public void updateLeaderboard(){

        TextView firstPlayerLevel=findViewById(R.id.tv_leaderboard_first_player_level);
        TextView firstPlayerPoints=findViewById(R.id.tv_leaderboard_first_player_points);

        TextView secondPlayerLevel=findViewById(R.id.tv_leaderboard_second_player_name);
        TextView secondPlayerPoints=findViewById(R.id.tv_leaderboard_second_player_points);

        TextView thirdPlayerLevel=findViewById(R.id.tv_leaderboard_third_player_name);
        TextView thirdPlayerPoints=findViewById(R.id.tv_leaderboard_third_player_points);

        TextView fourthPlayerLevel=findViewById(R.id.tv_leaderboard_fourth_player_name);
        TextView fourthPlayerPoints=findViewById(R.id.tv_leaderboard_fourth_player_points);

        TextView fifthPlayerLevel=findViewById(R.id.tv_leaderboard_fifth_player_name);
        TextView fifthPlayerPoints=findViewById(R.id.tv_leaderboard_fifth_player_points);

        db.collection("games")
                .orderBy("points", Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    Map<String,Object> map=new HashMap<>();
                    int index=0;
                    String p="",l="";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] valores=document.getData().values().toArray();
                                l=(valores[0].toString()); p=(valores[1].toString());

                                if(index==0){
                                    firstPlayerLevel.setText(l);
                                    firstPlayerPoints.setText(p);
                                }else if(index==1){
                                    secondPlayerLevel.setText(l);
                                    secondPlayerPoints.setText(p);
                                }else if(index==2){
                                    thirdPlayerLevel.setText(l);
                                    thirdPlayerPoints.setText(p);
                                }else if(index==3){
                                    fourthPlayerLevel.setText(l);
                                    fourthPlayerPoints.setText(p);
                                }else if(index==4){
                                    fifthPlayerLevel.setText(l);
                                    fifthPlayerPoints.setText(p);
                                }
                                index++;
                            }
                        }

                    }
                });

        pbLeaderboard.setVisibility(View.GONE);
    }


    public static Game[] getGameStats(File file){

        FileReaderAndWriter fraw=new FileReaderAndWriter();
        Game[] result = fraw.loadGames(file);

        return result;
    }
}