package com.example.mc.recycler_view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;

// Reference link for questions: http://www.placementquestion.com/category/computer_fundamentals/true_false_choice/2
//https://www.thatquiz.org/tq/previewtest?1/U/E/R/WKEB1364403372

public class Database_Helper extends SQLiteOpenHelper implements Serializable {

    Context context;
    private static final String DATABASE_NAME = "Quiz_Database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Quiz_Table";
    public static final String column_1 = "ques_no";
    public static final String column_2 = "ques_desc";
    public static final String column_3 = "ques_ans";
    private static final String create_table_query = "create table "+TABLE_NAME+"("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+column_2+" TEXT,"+column_3+" TEXT)";

    public static ArrayList<QuizQuestion> quiz_ques_arr;
    public Database_Helper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table_query);
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
        for(int i=0;i<30;i++){
            ContentValues cv = new ContentValues();
            cv.put("ques_desc",ques_arr.get(i));
            cv.put("ques_ans","null");
            sqLiteDatabase.insert(TABLE_NAME,null,cv);
        }
        //sqLiteDatabase.close();
    }

    public ArrayList<QuizQuestion> getAllQuestions(){
        ArrayList<QuizQuestion> result = new ArrayList<QuizQuestion>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                QuizQuestion m = new QuizQuestion(cursor.getString(1),cursor.getString(2),cursor.getInt(0));
                result.add(m);
            } while (cursor.moveToNext());
        }
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
    public void add_ques(String q){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ques_desc",q);
        cv.put("ques_ans","True");
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
**/
    public void edit_row(int qno,String quesd,String ans){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ques_no",qno);
        cv.put("ques_desc",quesd);
        cv.put("ques_ans",ans);
        db.update(TABLE_NAME,cv,"ques_no="+qno,new String[]{});
        //db.close();
    }

}
