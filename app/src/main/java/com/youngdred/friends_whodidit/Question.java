package com.youngdred.friends_whodidit;

public class Question {
    String questionText;
    String answer0;
    String answer1;
    String answer2;
    String answer3;
    int questionId;
    int correctAnswer=0;

    public Question(String questionText, String answer0, String answer1, String answer2, String answer3, int questionId, int correctAnswer) {
        this.questionText = questionText;
        this.answer0 = answer0;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.questionId=questionId;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer0() {
        return answer0;
    }

    public void setAnswer0(String answer0) {
        this.answer0 = answer0;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
