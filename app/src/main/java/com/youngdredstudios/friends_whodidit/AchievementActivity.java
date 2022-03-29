package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AchievementActivity extends AppCompatActivity {


    TextView titleAchievement1, descAchievement1, actProAchievement1, idAchievement1;
    ProgressBar pbA1, pbA2;
    View viewAchievement1,viewAchievement2,viewAchievement3;
    TextView titleAchievement2, descAchievement2, actProAchievement2, idAchievement2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        viewAchievement1=(View)findViewById(R.id.include_first_achievement);
        titleAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_achievement_title);
        descAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_achievement_desc);
        actProAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_progress_bar_achievement);
        idAchievement1=(TextView)viewAchievement1.findViewById(R.id.tv_achievement_id);
        pbA1=(ProgressBar)viewAchievement1.findViewById(R.id.progressBar_achievement_pct);

        viewAchievement2=(View)findViewById(R.id.include_second_achievement);
        titleAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_achievement_title);
        descAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_achievement_desc);
        actProAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_progress_bar_achievement);
        idAchievement2=(TextView)viewAchievement2.findViewById(R.id.tv_achievement_id);
        pbA2=(ProgressBar)viewAchievement2.findViewById(R.id.progressBar_achievement_pct);

        loadAchievements();
        loadUserAchievements();

    }

    public void loadAchievements(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("achievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String title=document.getString("Title");
                                String desc=document.getString("Desc");
                                String achievementId=document.getString("AchievementId");
                                int totalPoints=Integer.parseInt(String.valueOf(document.get("TotalProgress")));
                                Achievement achievement=new Achievement(desc,totalPoints,achievementId,title);
                                updateAchievement(achievement);
                            }
                        }
                    }
                });

    }

    public void updateAchievement(Achievement achievement){
        int index=Integer.parseInt(achievement.achievementId);
        switch (index){
            case 0:
                titleAchievement1.setText(achievement.title);
                descAchievement1.setText(achievement.desc);
                actProAchievement1.setText("0");
                idAchievement1.setText(achievement.achievementId);
                pbA1.setMax(achievement.totalProgress);
                break;
            case 1:
                titleAchievement2.setText(achievement.title);
                descAchievement2.setText(achievement.desc);
                actProAchievement2.setText("0");
                idAchievement2.setText(achievement.achievementId);
                pbA2.setMax(achievement.totalProgress);
                break;
        }
    }
    public void updateUserAchievement(UserAchievement achievement){
        switch (Integer.parseInt(achievement.achievementId)){
            case 0:
                pbA1.setProgress(achievement.actualProgress);
                break;
            case 1:
                pbA2.setProgress(achievement.actualProgress);
                break;
        }
    }

    public void loadUserAchievements(){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("userAchievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            int ind=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object[] values=document.getData().values().toArray();
                                UserAchievement userAchievement=new UserAchievement(values[1].toString(),
                                        Integer.parseInt(values[3].toString()),
                                        Boolean.parseBoolean(values[2].toString()),
                                        values[0].toString());
                                if (firebaseUser != null && values[0].toString().equals(firebaseUser.getUid())) {
                                    updateUserAchievement(userAchievement);
                                }
                                ind++;
                            }
                        }
                    }
                });

    }
}