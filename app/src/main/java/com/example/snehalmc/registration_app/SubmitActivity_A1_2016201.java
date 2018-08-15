package com.example.snehalmc.registration_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitActivity_A1_2016201 extends AppCompatActivity {
    public static String first = "State of SubmitActivity changed ";
    public static String from1 ="";
    public static String to1 ="";
    private static final String TAG = "SubmitActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__a1_2016201);
        to1 = "to Created.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(SubmitActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Created ";
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name_string = extras.getString("EXTRA_NAME");
        String rollno_string = extras.getString("EXTRA_ROLLNO");
        String branch_string = extras.getString("EXTRA_BRANCH");
        String c1_string = extras.getString("EXTRA_C1");
        String c2_string = extras.getString("EXTRA_C2");
        String c3_string = extras.getString("EXTRA_C3");
        String c4_string = extras.getString("EXTRA_C4");
        TextView t1 = findViewById(R.id.textView7);
        TextView t2 = findViewById(R.id.textView9);
        TextView t3 = findViewById(R.id.textView10);
        TextView t4 = findViewById(R.id.textView11);
        TextView t5 = findViewById(R.id.textView12);
        TextView t6 = findViewById(R.id.textView13);
        TextView t7 = findViewById(R.id.textView14);
        t1.setText("Entered Name: "+name_string);
        t2.setText("Entered Roll No: "+rollno_string);
        t3.setText("Entered Branch: "+branch_string);
        t4.setText("Entered Course 1: "+c1_string);
        t5.setText("Entered Course 2: "+c2_string);
        t6.setText("Entered Course 3: "+c3_string);
        t7.setText("Entered Course 4: "+c4_string);
    }

    @Override
    protected void onStart() {
        super.onStart();
        to1= "to Started.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(SubmitActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Started ";
    }
    @Override
    protected void onResume() {
        super.onResume();
        to1 = "to Resumed.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(SubmitActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Resumed ";
    }
    @Override
    protected void onPause() {
        super.onPause();
        to1 = "to Paused.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(SubmitActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Paused ";
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //to1 = "to Resumed.";
        //Log.d(TAG,first+from1+to1);
        //Toast.makeText(MainActivity.this,"ON RESTART", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        to1 = "to Stopped.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(SubmitActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Stopped ";
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        to1 = "to Destroyed.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(SubmitActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Destroyed ";
    }
}
