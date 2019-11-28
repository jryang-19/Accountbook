package com.example.loginregistrationwsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "user";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "BC.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text, expenses integer, setLimit integer, previous_month text, daily_expense text, dateFrom text, dateTo text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }
    //inserting in database
    public boolean insert (String email,String password,int expenses,int setLimit,String previous_month,String daily_expense,String dateFrom,String dateTo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("expenses",expenses);
        contentValues.put("setLimit",setLimit);
        contentValues.put("previous_month",previous_month);
        contentValues.put("daily_expense",daily_expense);
        contentValues.put("dateFrom",dateFrom);
        contentValues.put("dateTo",dateTo);
        long ins = db.insert("user",null,contentValues);
        if (ins==1) return false;
        else return true;

    }
    //checking if email exists
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    //Checking the email and password of login
    public Boolean emailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and password =?",new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;

    }

    //Return user password
    public String displayPW(String userEmail){
        String pw="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email ='"+ userEmail + "'", null);
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
        Cursor cursor = db.rawQuery("select * from user where email ='"+ userEmail + "'", null);
        if(cursor.moveToFirst()){
            limit = cursor.getString(3);
            return limit;
        }
        return limit;

    }

    //updateDb
    public boolean updateLimit(String email, int limit,String dateFrom,String dateTo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("setLimit",limit);
        contentValues.put("dateFrom",dateFrom);
        contentValues.put("dateTo",dateTo);
        db.update(TABLE_NAME, contentValues, "email = ?",new String[]{ email });
        return true;
    }




}
