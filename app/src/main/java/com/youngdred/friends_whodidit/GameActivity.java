package com.youngdred.friends_whodidit;

import static com.youngdred.friends_whodidit.R.drawable.chandler;
import static com.youngdred.friends_whodidit.R.drawable.joey;
import static com.youngdred.friends_whodidit.R.drawable.monica;
import static com.youngdred.friends_whodidit.R.drawable.phoebe;
import static com.youngdred.friends_whodidit.R.drawable.rachel;
import static com.youngdred.friends_whodidit.R.drawable.ross;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int answerSelected=-1;
    int currentQuestionIndex=0;

    private final Question[] questionBank=new Question[]{
        new Question("Who said \"we were on a break\"","Chandler","Ross","Monica",
                "Phoebe",0,1),
        new Question("Who showed up on a wedding dress in the first episode","Joey","Chandler","Ross",
                    "Rachel",1,3)
    };

    int bankSize=questionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button endGame = findViewById(R.id.btn_main_end_game);
        ImageButton answer0 = findViewById(R.id.img_btn_main_answer_0);
        ImageButton answer1 = findViewById(R.id.img_btn_main_answer_1);
        ImageButton answer2 = findViewById(R.id.img_btn_main_answer_2);
        ImageButton answer3 = findViewById(R.id.img_btn_main_answer_3);

        updateQuestion();

        answer0.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        endGame.setOnClickListener(this);
    }

    public void updatePoints(){
        TextView pointsTV = findViewById(R.id.tv_game_points_number);
        int points = Integer.parseInt((String) pointsTV.getText())+10;
        pointsTV.setText(String.valueOf(points));
    }

    public void endGameMethod(){
        TextView catv = findViewById(R.id.tv_main_correct_answers);
        String correctAnswersNumber = (String) (catv.getText());
        TextView tatv = findViewById(R.id.tv_main_total_answers);
        String totalAnswersNumber = (String) (tatv.getText());

        Intent summaryIntent= new Intent(GameActivity.this, FinishedGameSummary.class);
        summaryIntent.putExtra("Correct Answers",correctAnswersNumber);
        summaryIntent.putExtra("Total Answers",totalAnswersNumber);
        startActivity(summaryIntent);
    }

    public void addAnswer(boolean correct){

        TextView correctAnswersTV = findViewById(R.id.tv_main_correct_answers);
        TextView totalAnswersTV = findViewById(R.id.tv_main_total_answers);

        int correctAnswers=Integer.parseInt((String) correctAnswersTV.getText());
        int totalAnswers=Integer.parseInt((String) correctAnswersTV.getText());

        if(correct){
            correctAnswersTV.setText(String.valueOf(correctAnswers+1));
            updatePoints();
        }
        totalAnswersTV.setText(String.valueOf(totalAnswers+1));

    }

    public void updateQuestion(){

        TextView questionText = findViewById(R.id.tv_main_question_text);
        ImageButton answer0 = findViewById(R.id.img_btn_main_answer_0);
        ImageButton answer1 = findViewById(R.id.img_btn_main_answer_1);
        ImageButton answer2 = findViewById(R.id.img_btn_main_answer_2);
        ImageButton answer3 = findViewById(R.id.img_btn_main_answer_3);

        questionText.setText(questionBank[currentQuestionIndex].questionText);
        switch (questionBank[currentQuestionIndex].answer0){
            case "Joey": answer0.setBackgroundResource(joey); break;
            case "Chandler": answer0.setBackgroundResource(chandler); break;
            case "Monica": answer0.setBackgroundResource(monica); break;
            case "Ross": answer0.setBackgroundResource(ross); break;
            case "Rachel": answer0.setBackgroundResource(rachel); break;
            case "Phoebe": answer0.setBackgroundResource(phoebe); break;
        }
        switch (questionBank[currentQuestionIndex].answer1){
            case "Joey": answer1.setBackgroundResource(joey); break;
            case "Chandler": answer1.setBackgroundResource(chandler); break;
            case "Monica": answer1.setBackgroundResource(monica); break;
            case "Ross": answer1.setBackgroundResource(ross); break;
            case "Rachel": answer1.setBackgroundResource(rachel); break;
            case "Phoebe": answer1.setBackgroundResource(phoebe); break;
        }
        switch (questionBank[currentQuestionIndex].answer2){
            case "Joey": answer2.setBackgroundResource(joey); break;
            case "Chandler": answer2.setBackgroundResource(chandler); break;
            case "Monica": answer2.setBackgroundResource(monica); break;
            case "Ross": answer2.setBackgroundResource(ross); break;
            case "Rachel": answer2.setBackgroundResource(rachel); break;
            case "Phoebe": answer2.setBackgroundResource(phoebe); break;
        }
        switch (questionBank[currentQuestionIndex].answer3){
            case "Joey": answer3.setBackgroundResource(joey); break;
            case "Chandler": answer3.setBackgroundResource(chandler); break;
            case "Monica": answer3.setBackgroundResource(monica); break;
            case "Ross": answer3.setBackgroundResource(ross); break;
            case "Rachel": answer3.setBackgroundResource(rachel); break;
            case "Phoebe": answer3.setBackgroundResource(phoebe); break;
        }

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.img_btn_main_answer_0){
            answerSelected=0;
        } else if(view.getId()==R.id.img_btn_main_answer_1){
            answerSelected=1;
        }else if(view.getId()==R.id.img_btn_main_answer_2){
            answerSelected=2;
        }else if(view.getId()==R.id.img_btn_main_answer_3){
            answerSelected=3;
        }

        if(view.getId()==R.id.img_btn_main_answer_0||
                view.getId()==R.id.img_btn_main_answer_1||
                view.getId()==R.id.img_btn_main_answer_2||
                view.getId()==R.id.img_btn_main_answer_3){

            if(bankSize==currentQuestionIndex+1){
                if(answerSelected>=0&&answerSelected<4){
                    addAnswer(questionBank[currentQuestionIndex].correctAnswer == answerSelected);
                    answerSelected=-1;
                    endGameMethod();
                }
            }else{
                if(answerSelected>=0&&answerSelected<4){
                    addAnswer(questionBank[currentQuestionIndex].correctAnswer == answerSelected);
                    answerSelected=-1;
                    currentQuestionIndex++;
                }
            }

           updateQuestion();

        }

        if(view.getId()==R.id.btn_main_end_game){
            endGameMethod();
        }
    }
}