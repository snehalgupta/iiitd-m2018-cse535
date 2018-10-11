package com.example.mc.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizQuestionsAdapter extends RecyclerView.Adapter<QuizQuestionsAdapter.ViewHolder> {
    private ArrayList<QuizQuestion> ques_arr;

    public interface onItemClickListener{
        void onItemClick(QuizQuestion q);
    }

    private onItemClickListener onItemClickListener_var;

    public void setOnItemClickListener(onItemClickListener q){
        onItemClickListener_var = q;
    }

    public void postItemClick(ViewHolder holder){
        if(onItemClickListener_var != null){
            onItemClickListener_var.onItemClick(holder.ques);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private QuizQuestion ques;
        public final View mView;
        private QuizQuestion ques;
        private TextView ques_number;
/**
        public ViewHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.quiz_question_item,parent,false));
            itemView.setOnClickListener(this);
            ques_number = (TextView)itemView.findViewById(R.id.question);
        }
**/
        public ViewHolder(View v){
            super(v);
            mView = v;
            ques_number = (TextView)mView.findViewById(R.id.question);
            mView.setOnClickListener(this);
        }

        /**
        public void bind(QuizQuestion q){
            //ques = q;
            ques_number.setText(q.ques_desc);
        }
**/
        @Override
        public void onClick(View view) {
            postItemClick(this);
        }
    }

    public QuizQuestionsAdapter(ArrayList<QuizQuestion> arr){
        ques_arr = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_question_item, parent, false);
        return new ViewHolder(view);
        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //return new ViewHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.ques = ques_arr.get(position);
        holder.ques_number.setText("Question "+holder.ques.ques_no);
    }

    @Override
    public int getItemCount(){
        return ques_arr.size();
    }
}
