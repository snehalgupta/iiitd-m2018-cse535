package com.example.snehalmc.registration_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_A1_2016201 extends AppCompatActivity {

    public static String first = "State of MainActivity changed ";
    public static String from1 ="";
    public static String to1 ="";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__a1_2016201);
        to1 = "to Created.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(MainActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Created ";
    }
    @Override
    protected void onStart() {
        super.onStart();
        to1= "to Started.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(MainActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Started ";
    }
    @Override
    protected void onResume() {
        super.onResume();
        to1 = "to Resumed.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(MainActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Resumed ";
    }
    @Override
    protected void onPause() {
        super.onPause();
        to1 = "to Paused.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(MainActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(MainActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Stopped ";
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        to1 = "to Destroyed.";
        Log.d(TAG,first+from1+to1);
        Toast.makeText(MainActivity_A1_2016201.this,first+from1+to1, Toast.LENGTH_SHORT).show();
        from1="from Destroyed ";
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, SubmitActivity_A1_2016201.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        EditText editText1 = (EditText) findViewById(R.id.editText2);
        String message1 = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.editText3);
        String message2 = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.editText4);
        String message3 = editText3.getText().toString();
        EditText editText4 = (EditText) findViewById(R.id.editText5);
        String message4 = editText4.getText().toString();
        EditText editText5 = (EditText) findViewById(R.id.editText6);
        String message5 = editText5.getText().toString();
        EditText editText6 = (EditText) findViewById(R.id.editText7);
        String message6 = editText6.getText().toString();
        Bundle extras = new Bundle();
        extras.putString("EXTRA_NAME",message);
        extras.putString("EXTRA_ROLLNO",message1);
        extras.putString("EXTRA_BRANCH",message2);
        extras.putString("EXTRA_C1",message3);
        extras.putString("EXTRA_C2",message4);
        extras.putString("EXTRA_C3",message5);
        extras.putString("EXTRA_C4",message6);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void clear(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("");
        EditText editText1 = (EditText) findViewById(R.id.editText2);
        editText1.setText("");
        EditText editText2 = (EditText) findViewById(R.id.editText3);
        editText2.setText("");
        EditText editText3 = (EditText) findViewById(R.id.editText4);
        editText3.setText("");
        EditText editText4 = (EditText) findViewById(R.id.editText5);
        editText4.setText("");
        EditText editText5 = (EditText) findViewById(R.id.editText6);
        editText5.setText("");
        EditText editText6 = (EditText) findViewById(R.id.editText7);
        editText6.setText("");
    }
}
