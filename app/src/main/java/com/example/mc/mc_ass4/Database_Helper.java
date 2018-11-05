package com.example.mc.mc_ass4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Helper extends SQLiteOpenHelper{

    Context context;
    private static final String DATABASE_NAME = "Sensor_Database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_1 = "Acel_Table";
    public static final String TABLE_NAME_2 = "GPS_Table";
    public static final String TABLE_NAME_3 = "Gyro_Table";
    public static final String TABLE_NAME_4 = "Ori_Table";
    public static final String TABLE_NAME_5 = "Pro_Table";
    public static final String column_0 = "Timestamp";
    public static final String column_1 = "Serial_no";
    public static final String column_2 = "sensor_value_1";
    public static final String column_3 = "sensor_value_2";
    public static final String column_4 = "sensor_value_3";
    private static final String create_table_query_1 = "create table "+TABLE_NAME_1+"("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+column_0+" STRING,"+column_2+" STRING,"+column_3+" STRING,"+column_4+" STRING)";
    private static final String create_table_query_2 = "create table "+TABLE_NAME_2+"("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+column_0+" TEXT,"+column_2+" DECIMAL,"+column_3+" DECIMAL)";
    private static final String create_table_query_3 = "create table "+TABLE_NAME_3+"("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+column_0+" TEXT,"+column_2+" DECIMAL,"+column_3+" DECIMAL,"+column_4+" DECIMAL)";
    private static final String create_table_query_4 = "create table "+TABLE_NAME_4+"("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+column_0+" TEXT,"+column_2+" DECIMAL,"+column_3+" DECIMAL,"+column_4+" DECIMAL)";
    private static final String create_table_query_5 = "create table "+TABLE_NAME_5+"("+column_1+" INTEGER PRIMARY KEY AUTOINCREMENT"+","+column_0+" TEXT,"+column_2+" DECIMAL)";

    public Database_Helper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table_query_1);
        sqLiteDatabase.execSQL(create_table_query_2);
        sqLiteDatabase.execSQL(create_table_query_3);
        sqLiteDatabase.execSQL(create_table_query_4);
        sqLiteDatabase.execSQL(create_table_query_5);
    }

    /**
     public void getAllQuestions(){
     String selectQuery = "SELECT * FROM " + TABLE_NAME_1;
     SQLiteDatabase db = this.getWritableDatabase();
     Cursor cursor = db.rawQuery(selectQuery, null);
     if (cursor.moveToFirst()) {
     do {
     //QuizQuestion m = new QuizQuestion(cursor.getString(1),cursor.getString(2),cursor.getInt(0));
     //result.add(m);
     } while (cursor.moveToNext());
     }
     }

     **/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_5);
        onCreate(sqLiteDatabase);
    }

    public void add_acel(String s,float a,float b,float c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_0,s);
        cv.put(column_2,a);
        cv.put(column_3,b);
        cv.put(column_4,c);
        db.insert(TABLE_NAME_1,null,cv);
    }

    public void add_gps(String s,double a,double b){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_0,s);
        cv.put(column_2,a);
        cv.put(column_3,b);
        db.insert(TABLE_NAME_2,null,cv);
    }

    public void add_gyro(String s,float a,float b,float c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_0,s);
        cv.put(column_2,a);
        cv.put(column_3,b);
        cv.put(column_3,c);
        db.insert(TABLE_NAME_3,null,cv);
    }

    public void add_ori(String s,float a,float b,float c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_0,s);
        cv.put(column_2,a);
        cv.put(column_3,b);
        cv.put(column_4,c);
        db.insert(TABLE_NAME_4,null,cv);
    }

    public void add_pro(String s,float a){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_0,s);
        cv.put(column_2,a);
        db.insert(TABLE_NAME_5,null,cv);
    }

}