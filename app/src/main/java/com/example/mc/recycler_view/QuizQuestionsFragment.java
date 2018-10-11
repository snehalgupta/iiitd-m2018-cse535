package com.example.mc.recycler_view;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//import static android.support.v4.content.ContextCompat.getSystemService;

//import android.support.v7.app.AppCompatActivity;

//import static android.support.v4.content.ContextCompat.getSystemService;

public class QuizQuestionsFragment extends Fragment implements QuizQuestionsAdapter.onItemClickListener{

    private RecyclerView Quiz_recycler;
    private QuizQuestionsAdapter adapter;
    private Database_Helper db_helper;
    private Button btn;
    private ProgressDialog mProgressDialog;

    private class Upload_CSV_File extends AsyncTask<String,Integer,Void>{

        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            String filename = "Quiz_Database.csv";
            File file = new File(getContext().getFilesDir(), filename);

            try {
                OutputStream outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                ArrayList<QuizQuestion> ques_arr = db_helper.getAllQuestions();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.append(Database_Helper.column_1+","+Database_Helper.column_2+","+Database_Helper.column_3+"\n");
                for(int i=0;i<ques_arr.size();i++){
                    //Log.i("Yo","Tv"+ques_arr.get(i).ques_no+","+ques_arr.get(i).ques_desc+","+ques_arr.get(i).answer);
                    writer.append(ques_arr.get(i).ques_no+","+ques_arr.get(i).ques_desc+","+ques_arr.get(i).answer);
                    writer.append("\n");
                }
                writer.close();
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead,bufferSize;
                byte[] buffer;

                HttpURLConnection con = (HttpURLConnection) ( new URL("http://192.168.49.244/upload.php")).openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestProperty("Connection", "Keep-Alive");
                con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                con.setRequestProperty("ENCTYPE", "multipart/form-data");
                con.setRequestProperty("fileToUpload", filename);

                DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"fileToUpload\";filename=\""+ filename + "\"" + lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                FileInputStream fileInputStream = getContext().openFileInput(filename);
                bufferSize = (int) file.length()/1024;
                buffer = new byte[bufferSize];
                bytesRead = fileInputStream.read(buffer,0, bufferSize);
                int total = 0;
                while (bytesRead > 0) {
                    dataOutputStream.write(buffer,0,bufferSize);
                    total += bufferSize;
                    publishProgress((int)((total * 100) / file.length()));
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                int serverResponseCode = con.getResponseCode();
                String serverResponseMessage = con.getResponseMessage();
                Log.i("Message", "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i("message", "Yay! "+line);
                }

            } catch (Exception e) { e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.dismiss();
        }
    }

    public QuizQuestionsFragment() {

    }

    @Override
    public void onItemClick(QuizQuestion q){
        Question_Detail_Fragment frag = new Question_Detail_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",q);
        bundle.putSerializable("database",db_helper);
        frag.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,frag);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //String create_table_query = "create table "+db_helper.TABLE_NAME+"("+db_helper.column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+db_helper.column_2+" TEXT,"+db_helper.column_3+" TEXT)";
        //db_helper.getWritableDatabase().execSQL(create_table_query);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_questions,container,false);
        Quiz_recycler = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        Quiz_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        db_helper = new Database_Helper(getActivity());
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setTitle("AsyncTask");
        mProgressDialog.setMessage("Uploading file...");
        updateUI();
        btn = (Button)view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    Log.i("Tag","Started");
                    new Upload_CSV_File().execute("");
                } else {
                    Toast.makeText(getContext(),"Please check network connection.",Toast.LENGTH_SHORT).show();
                }
                ArrayList<QuizQuestion> arr = db_helper.getAllQuestions();
                for(int i=0;i<30;i++){
                    db_helper.edit_row(i+1,arr.get(i).ques_desc,"null");
                }
            }
        });
        return view;
    }

    private void updateUI(){
        ArrayList<QuizQuestion> arr = db_helper.getAllQuestions();
        adapter = new QuizQuestionsAdapter(arr);
        adapter.setOnItemClickListener(this);
        Quiz_recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
