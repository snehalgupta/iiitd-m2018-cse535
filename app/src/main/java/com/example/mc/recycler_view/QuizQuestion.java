package com.example.mc.recycler_view;

import java.io.Serializable;

public class QuizQuestion implements Serializable {
    String ques_desc;
    String answer;
    int ques_no;

    public QuizQuestion(String question,String tr,int w) {
        this.ques_desc = question;
        this.answer = tr;
        this.ques_no = w;
    }
}
