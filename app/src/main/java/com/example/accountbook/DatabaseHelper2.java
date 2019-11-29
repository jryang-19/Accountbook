package com.example.accountbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "day";

    public DatabaseHelper2(@Nullable Context context) {
        super(context, "BC.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table day(year integer primary key, month integer, date integer , daily_expense integer, categories integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists day");
        onCreate(db);
    }
    //inserting in database
    public boolean insert (int year,int month,int date,int daily_expense, int categories){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("year",year);
        contentValues.put("month",month);
        contentValues.put("date",date);
        contentValues.put("daily_expense",daily_expense);
        contentValues.put("categories",categories);

        long ins = db.insert("",null,contentValues);
        if (ins==1) return false;
        else return true;

    }
    //checking if email exists


    //Checking the email and password of login


    //Return day- year
    public String displayPW(int year){
        String pw="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from day where year ='"+ year + "'", null);
        if(cursor.moveToFirst()){
            pw = cursor.getString(1);
            return pw;
        }
        return pw;

    }

    //Return user limit
    public String displayLimit(String userEmail){
        String limit="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from day where email ='"+ userEmail + "'", null);
        if(cursor.moveToFirst()){
            limit = cursor.getString(3);
            return limit;
        }
        return limit;

    }

    //updateDb


}
