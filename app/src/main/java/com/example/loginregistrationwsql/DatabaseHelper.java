package com.example.loginregistrationwsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.StringTokenizer;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "user";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "BC.db", null, 1);
    }

    @Override//db create
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text, password text, setLimit integer, dateFrom text, dateTo text, set_valid text, year integer, month integer, day integer, price integer, category integer, image_id integer, num_day integer )");
        // first: name. second: type
    }

    @Override //when upgrade db
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }
    //inserting in database // insert 성공하면 1.
    public boolean insert (String email, String password, int setLimit, String dateFrom, String dateTo, String set_valid, int year, int month, int day, int price, int category, int image_id, int num_day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("setLimit", setLimit);
        contentValues.put("dateFrom", dateFrom);
        contentValues.put("dateTo", dateTo);
        contentValues.put("set_valid", set_valid);
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("day", day);
        contentValues.put("price", price);
        contentValues.put("category", category);
        contentValues.put("image_id", image_id);
        contentValues.put("num_day", num_day);
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

    //update limit
    public boolean updateLimit(String email, int limit, String dateFrom, String dateTo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("setLimit",limit);
        contentValues.put("dateFrom",dateFrom);
        contentValues.put("dateTo",dateTo);

        long update = db.update(TABLE_NAME, contentValues, "set_valid = ?", new String[]{ email });
        if(update != -1){
            return true;
        }
        else
            return false;
    }

    public boolean deleteLimit(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("setLimit", 0);
        contentValues.put("dateFrom", "");
        contentValues.put("dateTo", "");

        //db.execSQL("delete from user where set_valid = ?", new String[]{email});
        long delete = db.delete(TABLE_NAME,  "set_valid = ?", new String[]{ email });
        if(delete != -1){
            return true;
        }
        else
            return false;
    }

    public String showLimit(String email){
        String res = "";
        String datefrom;
        String dateto;
        int limit;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and set_valid =?",new String[]{email, email});
        if(cursor.moveToFirst()){
            datefrom =  cursor.getString(3);
            dateto = cursor.getString(4);
            limit = cursor.getInt(2);
            res = datefrom+" ~ "+dateto+": "+limit;
        }
        else
            res = "No limitation set";

        return res;
    }

    public int[] day_expanse_category(String email, int year, int month, int day) {
        int[] category = new int[4];
        for (int i = 0; i < 4; i++) {
            category[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and day=? and month=? and year=?", new String[]{email, "" + day,  "" + month, "" + year});
        while(cursor.moveToNext()) {
            category[cursor.getInt(10)] += cursor.getInt(9);
        }
        cursor.close();
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
            category[cursor.getInt(10)] += cursor.getInt(9);
        }
        cursor.close();
        return category;
    }

    public int month_expanse(String email, int year, int month){
        int res = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and month=? and year=?", new String[]{email, ""+month, ""+year});
        while(cursor.moveToNext()){
            res += cursor.getInt(9);
        }
        cursor.close();
        return res;
    }

    public int day_expanse(String email, int year, int month, int day){
        int res = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and day=? and month=? and year=?", new String[]{email, ""+day,  ""+month, ""+year});
        while(cursor.moveToNext()){
            res += cursor.getInt(9);
        }
        cursor.close();
        return res;
    }

    public Boolean checkLimit(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and set_valid =?",new String[]{email, email});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public String push_title(String email){
        String res = "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and set_valid =?",new String[]{email, email});
        if(cursor.moveToFirst()){
            res = res + cursor.getString(3) + " ~ " + cursor.getString(4) + ": " + cursor.getInt(2);
        }
        cursor.close();
        return res;
    }

    public String push_text(String email){
        String s_res = "";
        int tmp = 0;
        String datefrom;
        String dateto;
        int limit = 0;
        float res;
        int[] date_from = new int[3];
        int[] date_to = new int[3];
        int i = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and set_valid =?",new String[]{email, email});
        if(cursor.moveToFirst()){
            datefrom =  cursor.getString(3);
            dateto = cursor.getString(4);
            limit = cursor.getInt(2);
        }
        else{
            return s_res;
        }
        cursor.close();

        StringTokenizer st1 = new StringTokenizer(datefrom, "/");
        StringTokenizer st2 = new StringTokenizer(dateto, "/");

        while(st1.hasMoreTokens()){
            date_from[i]= Integer.parseInt(st1.nextToken());
            i++;
        }
        i = 0;
        while(st2.hasMoreTokens()){
            date_to[i]= Integer.parseInt(st2.nextToken());
            i++;
        }

        while(true){
            tmp += day_expanse(email, date_from[0], date_from[1], date_from[2]);
            date_from[2] += 1;
            if(date_from[2] == 32) {
                date_from[2] = 1;
                date_from[1] += 1;
            }
            if(date_from[1] == 13){
                date_from[1] = 1;
                date_from[0] += 1;
            }

            if(date_from[0] == date_to[0] && date_from[1] == date_to[1] && date_from[2] == date_to[2]+1)
                break;
        }
        s_res = " Current: " + tmp + "           ";
        if(tmp < limit) {
            res = (float)(tmp / (float)limit) * 100;
            s_res = s_res + res + "%";
            return s_res;
        }
        else {
            s_res = s_res + "Over limitation!!";
            return s_res;
        }
    }

    public int num_day(String email, int year, int month, int day){
        int res = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and day=? and month=? and year=?", new String[]{email, ""+day,  ""+month, ""+year});
        res = cursor.getCount();
        return res;
    }

}
