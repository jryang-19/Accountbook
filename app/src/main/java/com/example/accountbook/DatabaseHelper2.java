package com.example.accountbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "expanse";

    public DatabaseHelper2(@Nullable Context context) {
        super(context, "BC.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table day(year integer primary key, month integer, day integer , price integer, categories integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists day");
        onCreate(db);
    }
    //inserting in database
    public boolean insert (int year, int month, int day, int price, int categories){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("year", year);
        contentValues.put("month",month);
        contentValues.put("day", day);
        contentValues.put("price", price);
        contentValues.put("categories", categories);

        long ins = db.insert("",null,contentValues);
        if (ins==1) return false;
        else return true;

    }

    public int[] day_expanse_category(int year, int month, int day){
        int[] category = new int[3];
        for(int i=0; i<3; i++) {
            category[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expanse where day = '"+ day + "'", new String[]{"%" + day + "%"});
        while(cursor.moveToNext()){
            if(cursor.getInt(1) == year && cursor.getInt(2) == month){
                category[cursor.getInt(3)] += cursor.getInt(4);
            }
        }
        return category;
    }

    public int[] month_expanse_category(int year, int month){
        int[] category = new int[3];
        for(int i=0; i<3; i++) {
            category[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expanse where month = '"+ month + "'", new String[]{"%" + month + "%"});
        while(cursor.moveToNext()){
            if(cursor.getInt(1) == year){
                category[cursor.getInt(3)] += cursor.getInt(4);
            }
        }
        return category;
    }

    public int month_expanse(int year, int month){
        int res = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expanse where "+ month + " = ?", new String[]{"%" + month + "%"});
        while(cursor.moveToNext()){
            if(cursor.getInt(1) == year){
                res += cursor.getInt(4);
            }
        }
        return res;
    }


}
