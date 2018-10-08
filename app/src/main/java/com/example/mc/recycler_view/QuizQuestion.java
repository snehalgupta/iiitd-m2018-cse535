package com.example.mc.recycler_view;

public class QuizQuestion {
    String ques_desc;
    String answer;
    static int ques_count=0;
    int ques_no;

    public QuizQuestion(String question) {
        ques_count++;
        this.ques_desc = question;
        this.answer = "True";
        this.ques_no = ques_count;
    }
}
