package com.youngdred.friends_whodidit;

import static com.youngdred.friends_whodidit.R.drawable.chandler;
import static com.youngdred.friends_whodidit.R.drawable.friends_banner;
import static com.youngdred.friends_whodidit.R.drawable.joey;
import static com.youngdred.friends_whodidit.R.drawable.monica;
import static com.youngdred.friends_whodidit.R.drawable.phoebe;
import static com.youngdred.friends_whodidit.R.drawable.rachel;
import static com.youngdred.friends_whodidit.R.drawable.ross;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int answerSelected=-1;
    int currentQuestionIndex=0;

    private Question[] questionBank=new Question[]{
        new Question("Who said \"we were on a break\"","Chandler","Ross","Monica",
                "Phoebe",0,1),
        new Question("Who showed up on a wedding dress in the first episode","Joey","Chandler","Ross",
                    "Rachel",1,3)
    };

    int bankSize=questionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView correctAnswers = findViewById(R.id.tv_main_correct_answers);
        TextView totalAnswers = findViewById(R.id.tv_main_total_answers);
        Button endGame = findViewById(R.id.btn_main_end_game);
        ImageButton answer0 = findViewById(R.id.img_btn_main_answer_0);
        ImageButton answer1 = findViewById(R.id.img_btn_main_answer_1);
        ImageButton answer2 = findViewById(R.id.img_btn_main_answer_2);
        ImageButton answer3 = findViewById(R.id.img_btn_main_answer_3);
        ImageView cover = findViewById(R.id.imv_main_game_cover);
        TextView questionText = findViewById(R.id.tv_main_question_text);
        Button submit = findViewById(R.id.btn_main_submit);

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

        answer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected=0;
            }
        });
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected=1;
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected=2;
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected=3;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bankSize==currentQuestionIndex+1){
                    if(answerSelected>=0&&answerSelected<4){
                        if(questionBank[currentQuestionIndex].correctAnswer==answerSelected){
                            correctAnswers.setText(addAnswer(Integer.parseInt((String) correctAnswers.getText())));
                        }
                        totalAnswers.setText(addAnswer(Integer.parseInt((String) totalAnswers.getText())));
                        answerSelected=-1;
                        currentQuestionIndex=0;
                        questionText.setText(questionBank[currentQuestionIndex].questionText);
                    }
                }else{
                    if(answerSelected>=0&&answerSelected<4){
                        if(questionBank[currentQuestionIndex].correctAnswer==answerSelected){
                            correctAnswers.setText(addAnswer(Integer.parseInt((String) correctAnswers.getText())));
                        }
                        totalAnswers.setText(addAnswer(Integer.parseInt((String) totalAnswers.getText())));
                        answerSelected=-1;
                        currentQuestionIndex++;
                        questionText.setText(questionBank[currentQuestionIndex].questionText);
                    }
                }

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
        });

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent summaryIntent= new Intent(MainActivity.this, FinishedGameSummary.class);
                summaryIntent.putExtra("Correct Answers",String.valueOf(correctAnswers.getText()));
                summaryIntent.putExtra("Total Answers",(String) totalAnswers.getText());
                startActivity(summaryIntent);
            }
        });
    }

    public String addAnswer(int totalAnswers){
        return String.valueOf(totalAnswers+1);
    }


}