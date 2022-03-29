package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AchievementActivity extends AppCompatActivity {


    TextView titleAchievement1, descAchievement1, actProAchievement1, idAchievement1;
    ProgressBar pbA1, pbA2, pbA3;
    View viewAchievement1,viewAchievement2,viewAchievement3;
    TextView titleAchievement2, descAchievement2, actProAchievement2, idAchievement2;
    TextView titleAchievement3, descAchievement3, actProAchievement3, idAchievement3;

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

        viewAchievement3=(View)findViewById(R.id.include_third_achievement);
        titleAchievement3=(TextView)viewAchievement3.findViewById(R.id.tv_achievement_title);
        descAchievement3=(TextView)viewAchievement3.findViewById(R.id.tv_achievement_desc);
        actProAchievement3=(TextView)viewAchievement3.findViewById(R.id.tv_progress_bar_achievement);
        idAchievement3=(TextView)viewAchievement3.findViewById(R.id.tv_achievement_id);
        pbA3=(ProgressBar)viewAchievement3.findViewById(R.id.progressBar_achievement_pct);

        loadAchievements();
        loadUserAchievements(3);

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
            case 2:
                titleAchievement3.setText(achievement.title);
                descAchievement3.setText(achievement.desc);
                actProAchievement3.setText("0");
                idAchievement3.setText(achievement.achievementId);
                pbA3.setMax(achievement.totalProgress);
                break;
        }
    }
    public void updateUserAchievement(UserAchievement achievement){
        switch (Integer.parseInt(achievement.achievementId)){
            case 0:
                pbA1.setProgress(achievement.actualProgress);
                actProAchievement1.setText(String.valueOf(achievement.actualProgress));
                break;
            case 1:
                pbA2.setProgress(achievement.actualProgress);
                actProAchievement2.setText(String.valueOf(achievement.actualProgress));
                break;
            case 2:
                pbA3.setProgress(achievement.actualProgress);
                actProAchievement3.setText(String.valueOf(achievement.actualProgress));
                break;
        }
    }

    public void loadUserAchievements(int numberAchievements){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("userAchievements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Boolean> existUserAchievement=new ArrayList<>();
                        for(int i=0;i<numberAchievements;i++){
                            existUserAchievement.add(false);
                        }
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String achievementID=String.valueOf(document.get("AchievementID"));
                                String userId=document.getString("UserId");
                                int actual=Integer.parseInt(String.valueOf(document.get("ActualProgress")));
                                boolean completed=Boolean.parseBoolean(String.valueOf(document.get("Completed")));
                                UserAchievement userAchievement=new UserAchievement(achievementID,actual,completed,userId);
                                if (firebaseUser != null && userId.equals(firebaseUser.getUid())&& Integer.parseInt(userAchievement.achievementId)<numberAchievements) {
                                    updateUserAchievement(userAchievement);
                                    existUserAchievement.set(Integer.parseInt(userAchievement.achievementId),true);
                                }
                            }
                            createEmptyUserAchievement(existUserAchievement);
                        }
                    }
                });

    }

    public void createEmptyUserAchievement(ArrayList<Boolean> eua){
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        for(int index=0;index<eua.size();index++){
            if(!eua.get(index)){
                Map<String,Object> userAchieve=new HashMap<>();
                userAchieve.put("AchievementID",index);
                userAchieve.put("ActualProgress",0);
                userAchieve.put("Completed",false);
                userAchieve.put("UserId",firebaseUser.getUid());

                db.collection("userAchievements")
                        .add(userAchieve)
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
        }

    }
}