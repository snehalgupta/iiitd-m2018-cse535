package com.example.mc.recycler_view;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database_Helper extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "Quiz_Database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Quiz_Table";
    private static final String create_table_query = "create table "+TABLE_NAME+"(ques_no INTEGER,ques_desc TEXT,ques_ans TEXT)";

    public Database_Helper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void add_ques(QuizQuestion q){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ques_no",q.ques_no);
        cv.put("ques_desc",q.ques_desc);
        cv.put("ques_ans",q.answer);
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
}
