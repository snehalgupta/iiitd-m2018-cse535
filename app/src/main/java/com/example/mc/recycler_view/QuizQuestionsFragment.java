package com.example.mc.recycler_view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class QuizQuestionsFragment extends Fragment implements QuizQuestionsAdapter.onItemClickListener {

    private RecyclerView Quiz_recycler;
    private QuizQuestionsAdapter adapter;
    private Database_Helper db_helper;

    public QuizQuestionsFragment() {

    }

    @Override
    public void onItemClick(QuizQuestion q){
        Question_Detail_Fragment frag = new Question_Detail_Fragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(this);
        ft.replace(R.id.fragment_container,frag);
        ft.commitNow();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_questions,container,false);
        Quiz_recycler = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        Quiz_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI(){
        ArrayList<String> ques_arr = new ArrayList<String>();
        ques_arr.add("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.");
        ques_arr.add("Freeware is software that is available for use at no monetary cost.");
        ques_arr.add("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.");
        ques_arr.add("The hexadecimal number system contains digits from 1 - 15.");
        ques_arr.add("Octal number system contains digits from 0 - 7.");
        ques_arr.add("MS Word is a hardware.");
        ques_arr.add("CPU controls only input data of computer.");
        ques_arr.add("CPU stands for Central Performance Unit.");
        ques_arr.add("The Language that the computer can understand is called Machine Language.");
        ques_arr.add("Magnetic Tape used random access method.");
        ques_arr.add("Twitter is an online social networking and blogging service.");
        ques_arr.add("Worms and trojan horses are easily detected and eliminated by antivirus software.");
        ques_arr.add("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.");
        ques_arr.add("GNU / Linux is a open source operating system.");
        ques_arr.add("When you include multiple addresses in a message, you should separate each address with a period (.).");
        ques_arr.add("You cannot format text in an e-mail message.");
        ques_arr.add("If you want to respond to the sender of a message, click the Respond button.");
        ques_arr.add("You type the body of a reply the same way you would type the body of a new message.");
        ques_arr.add("When you reply to a message, you need to enter the text in the Subject: field.");
        ques_arr.add("You can only print one copy of a selected message.");
        ques_arr.add("You cannot preview a message before you print it.");
        ques_arr.add("There is only one way to print a message.");
        ques_arr.add("When you print a message, it is automatically deleted from your Inbox.");
        ques_arr.add("You need to delete a contact and creat a new one to change contact information.");
        ques_arr.add("You must complete all fields in the Contact form before you can save the contact.");
        ques_arr.add("You cannot edit Contact forms.");
        ques_arr.add("You should always open and attachment before saving it.");
        ques_arr.add("All attachment are safe.");
        ques_arr.add("It is impossible to send a worm or virus over the Internet using in attachment.");
        ques_arr.add("You can only send one attachment per e-mail message.");
        ArrayList<QuizQuestion> arr = new ArrayList<QuizQuestion>();
        db_helper = new Database_Helper(getActivity());
        for(int i=0;i<30;i++){
            QuizQuestion ques = new QuizQuestion(ques_arr.get(i));
            //db_helper.add_ques(ques);
            arr.add(ques);
        }
        adapter = new QuizQuestionsAdapter(arr);
        adapter.setOnItemClickListener(this);
        Quiz_recycler.setAdapter(adapter);
    }

}
