package com.example.loginregistrationwsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME1 = "user";
    public static final String TABLE_NAME2 = "expanse";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "BC.db", null, 1);
    }

    @Override//db create
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text, password text, setLimit integer, dateFrom text, dateTo text, year integer, month integer, day integer, price integer, category integer )");
        // first: name. second: type
    }

    @Override //when upgrade db
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }
    //inserting in database // insert 성공하면 1.
    public boolean insert (String email, String password, int setLimit, String dateFrom, String dateTo, int year, int month, int day, int price, int category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("setLimit", setLimit);
        contentValues.put("dateFrom", dateFrom);
        contentValues.put("dateTo", dateTo);
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("day", day);
        contentValues.put("price", price);
        contentValues.put("category", category);
        long ins = db.insert("user",null, contentValues); // table 이름,nullColumnHack = DB에 비어있는 값이 못들어가서 비어있으면 null값을 넣음, 생성해놓은 데이터 맵
        if (ins==-1) return false;
        else return true;
    }

    //checking if email exists
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email}); //cursor은 커서로 각 행을 가르킴. 처음엔 첫 행.//rawQuery는 SELECT 문으로 데이터베이스를 조회할때
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

    //Return user expense
    public int retrieveExpense(String userEmail){
        String stringExpense;
        int expense=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email ='"+ userEmail + "'", null);
        if(cursor.moveToFirst()){
            stringExpense = cursor.getString(2);
            expense = Integer.valueOf(stringExpense);
            return expense;
        }
        return expense;

    }

    //update limit
    public boolean updateLimit(String email, int limit,String dateFrom,String dateTo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("setLimit",limit);
        contentValues.put("dateFrom",dateFrom);
        contentValues.put("dateTo",dateTo);
        db.update(TABLE_NAME1, contentValues, "email = ?",new String[]{ email });
        return true;
    }

    //set limit to zero
    public boolean setLimitToZero(String email, int limit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("setLimit",limit);
        db.update(TABLE_NAME1, contentValues, "email = ?",new String[]{ email });
        return true;
    }

    public int[] day_expanse_category(String email, int year, int month, int day) {
        int[] category = new int[4];
        for (int i = 0; i < 4; i++) {
            category[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and day=? and month=? and year=?", new String[]{email, "" + day,  "" + month, "" + year});
        while(cursor.moveToNext()) {
            category[cursor.getInt(9)] += cursor.getInt(8);
        }
        return category;
    }

    public int[] month_expanse_category(String email, int year, int month){
        int[] category = new int[4];
        for(int i=0; i<4; i++) {
            category[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and month=? and year=?", new String[]{email, ""+month, ""+year});
        while(cursor.moveToNext()){
            category[cursor.getInt(9)] += cursor.getInt(8);
        }
        return category;
    }

    public int month_expanse(String email, int year, int month){
        int res = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and month=? and year=?", new String[]{email, ""+month, ""+year});
        while(cursor.moveToNext()){
            res += cursor.getInt(8);
        }
        return res;
    }

}
