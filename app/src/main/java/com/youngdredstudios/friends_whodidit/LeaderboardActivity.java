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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

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

        TextView secondPlayerLevel=findViewById(R.id.tv_leaderboard_second_player_level);
        TextView secondPlayerPoints=findViewById(R.id.tv_leaderboard_second_player_points);

        TextView thirdPlayerLevel=findViewById(R.id.tv_leaderboard_third_player_level);
        TextView thirdPlayerPoints=findViewById(R.id.tv_leaderboard_third_player_points);

        TextView fourthPlayerLevel=findViewById(R.id.tv_leaderboard_fourth_player_level);
        TextView fourthPlayerPoints=findViewById(R.id.tv_leaderboard_fourth_player_points);

        TextView fifthPlayerLevel=findViewById(R.id.tv_leaderboard_fifth_player_level);
        TextView fifthPlayerPoints=findViewById(R.id.tv_leaderboard_fifth_player_points);


        db.collection("games")
                .orderBy("points", Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    int index=0;
                    String p="",l="",u="";

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] values=document.getData().values().toArray();
                                l=(values[0].toString()); p=(values[2].toString());u=(values[1].toString());

                                if(index==0){
                                    firstPlayerLevel.setText(l);
                                    firstPlayerPoints.setText(p);
                                    getUsernameChanged(u,0);

                                }else if(index==1){
                                    secondPlayerLevel.setText(l);
                                    secondPlayerPoints.setText(p);
                                    getUsernameChanged(u,1);
                                }else if(index==2){
                                    thirdPlayerLevel.setText(l);
                                    thirdPlayerPoints.setText(p);
                                    getUsernameChanged(u,2);
                                }else if(index==3){
                                    fourthPlayerLevel.setText(l);
                                    fourthPlayerPoints.setText(p);
                                    getUsernameChanged(u,3);
                                }else if(index==4){
                                    fifthPlayerLevel.setText(l);
                                    fifthPlayerPoints.setText(p);
                                    getUsernameChanged(u,4);
                                }
                                index++;
                            }
                        }

                    }
                });

        pbLeaderboard.setVisibility(View.GONE);
    }

    public void getUsernameChanged(String userID, int index){

        db.collection("usernames")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean found=false;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] valores=document.getData().values().toArray();
                                if(valores[2].toString().equals(userID)){
                                    String username =valores[1].toString();
                                    updateUsername(username, index);
                                    found=true;
                                }
                            }
                            if(!found){
                                updateUsername("Anonymus",index);
                            }
                        }
                    }
                });
    }

    public void updateUsername(String user, int index){
        switch (index){
            case 0:
                TextView firstPlayerName=findViewById(R.id.tv_leaderboard_first_player_name);
                firstPlayerName.setText(user);
                break;
            case 1:
                TextView secondPlayerName=findViewById(R.id.tv_leaderboard_second_player_name);
                secondPlayerName.setText(user);
                break;
            case 2:
                TextView thirdPlayerName=findViewById(R.id.tv_leaderboard_third_player_name);
                thirdPlayerName.setText(user);
                break;
            case 3:
                TextView fourthPlayerName=findViewById(R.id.tv_leaderboard_fourth_player_name);
                fourthPlayerName.setText(user);
                break;
            case 4:
                TextView fifthPlayerName=findViewById(R.id.tv_leaderboard_fifth_player_name);
                fifthPlayerName.setText(user);

        }
    }

}