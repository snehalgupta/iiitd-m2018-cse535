package com.example.mc.recycler_view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Question_Detail_Fragment extends Fragment {

    private TextView ques;
    private Button btn;
    private RadioGroup rad_grp;
    private Database_Helper db_helper;

    public Question_Detail_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_, container, false);
        ques = (TextView)view.findViewById(R.id.textView);
        btn = (Button)view.findViewById(R.id.button);
        rad_grp = (RadioGroup) view.findViewById(R.id.RGroup);
        Bundle bundle = getArguments();
        QuizQuestion q = (QuizQuestion) bundle.getSerializable("object");
        final String quesd = q.ques_desc;
        final int qno=q.ques_no;
        db_helper = (Database_Helper) bundle.getSerializable("database");
        String desc = "Question "+q.ques_no+'\n'+q.ques_desc;
        ques.setText(desc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = rad_grp.getCheckedRadioButtonId();
                Log.i("selected",selected+"ty_selected");
                if(selected != 2131165187){
                    db_helper.edit_row(qno,quesd,"True");
                }
                else{
                    db_helper.edit_row(qno,quesd,"False");
                }
                getFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }



}
