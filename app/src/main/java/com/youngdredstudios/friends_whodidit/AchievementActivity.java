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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AchievementActivity extends AppCompatActivity {


    TextView titleAchievement1, descAchievement1, actProAchievement1;
    ProgressBar pbA1, pbA2;
    View viewAchievement1,viewAchievement2,viewAchievement3;
    TextView titleAchievement2, descAchievement2, actProAchievement2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        viewAchievement1=(View)findViewById(R.id.include_first_achievement);
        titleAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_achievement_title);
        descAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_achievement_desc);
        actProAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_progress_bar_achievement);
        pbA1=(ProgressBar)viewAchievement1.findViewById(R.id.progressBar_achievement_pct);

        viewAchievement2=(View)findViewById(R.id.include_second_achievement);
        titleAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_achievement_title);
        descAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_achievement_desc);
        actProAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_progress_bar_achievement);
        pbA2=(ProgressBar)viewAchievement2.findViewById(R.id.progressBar_achievement_pct);

        loadAchievements();

    }

    public void loadAchievements(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("achievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int ind=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] valores=document.getData().values().toArray();
                                Achievement achievement=new Achievement(valores[0].toString(),
                                        Integer.parseInt(valores[1].toString()),valores[2].toString(),
                                        valores[3].toString());
                                Log.d("TAG_VALUES_DOCU",document.getData().values().toString());
                                updateAchievement(ind,achievement);
                                ind++;
                            }
                        }
                    }
                });
        int index=0;

    }

    public void updateAchievement(int index, Achievement achievement){
        switch (index){
            case 0:
                titleAchievement1.setText(achievement.title);
                descAchievement1.setText(achievement.desc);
                actProAchievement1.setText("0");
                pbA1.setMax(achievement.totalProgress);
                break;
            case 1:
                titleAchievement2.setText(achievement.title);
                descAchievement2.setText(achievement.desc);
                actProAchievement2.setText("0");
                pbA2.setMax(achievement.totalProgress);
                break;
        }
    }
}