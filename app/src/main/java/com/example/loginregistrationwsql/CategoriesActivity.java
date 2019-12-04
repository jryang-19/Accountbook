package com.example.loginregistrationwsql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle bun;
    String name;
    String PW;
    int year;
    int month;
    int day;
    int category;
    public static Activity cateact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cateact = CategoriesActivity.this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Intent intent = getIntent();

        bun = intent.getExtras();
        year = bun.getInt("year");
        month = bun.getInt("month");
        day = bun.getInt("day");
        name = bun.getString("name");
        PW = bun.getString("PW");

        findViewById(R.id.ImageView_food).setOnClickListener(this);
        findViewById(R.id.ImageView_drink).setOnClickListener(this);
        findViewById(R.id.ImageView_Transportation).setOnClickListener(this);
        findViewById(R.id.ImageView_Gift).setOnClickListener(this);
        findViewById(R.id.ImageView_Clothes).setOnClickListener(this);
        findViewById(R.id.ImageView_Fees).setOnClickListener(this);
        findViewById(R.id.ImageView_Education).setOnClickListener(this);
        findViewById(R.id.ImageView_Entertainment).setOnClickListener(this);
        findViewById(R.id.ImageView_Groceries).setOnClickListener(this);
        findViewById(R.id.ImageView_Health).setOnClickListener(this);
        findViewById(R.id.ImageView_Beauty).setOnClickListener(this);
        findViewById(R.id.ImageView_Others).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        int resourceId = 0;
        switch ((v.getId())) {
            case R.id.ImageView_food:
                resourceId = R.drawable.food;
                category = 1;
                break;
            case R.id.ImageView_drink:
                resourceId = R.drawable.cafe;
                category = 2;
                break;
            case R.id.ImageView_Transportation:
                resourceId = R.drawable.bus;
                category = 3;
                break;
            case R.id.ImageView_Gift:
                resourceId = R.drawable.gift;
                category = 4;
                break;
            case R.id.ImageView_Clothes:
                resourceId = R.drawable.clothes;
                category = 5;
                break;
            case R.id.ImageView_Fees:
                resourceId = R.drawable.fees;
                category = 6;
                break;
            case R.id.ImageView_Education:
                resourceId = R.drawable.education;
                category = 7;
                break;
            case R.id.ImageView_Entertainment:
                resourceId = R.drawable.entertainment;
                category = 8;
                break;
            case R.id.ImageView_Groceries:
                resourceId = R.drawable.groceries;
                category = 9;
                break;
            case R.id.ImageView_Health:
                resourceId = R.drawable.health;
                category = 10;
                break;
            case R.id.ImageView_Beauty:
                resourceId = R.drawable.beauty;
                category = 11;
                break;
            case R.id.ImageView_Others:
                resourceId = R.drawable.other;
                category = 12;
                break;
        }
        if (resourceId > 0){
            Intent intent2 = new Intent(CategoriesActivity.this, CountActivity.class);
            intent2.putExtra("resourceId", resourceId);
            intent2.putExtra("name", name);
            intent2.putExtra("PW", PW);
            intent2.putExtra("year", year);
            intent2.putExtra("month", month);
            intent2.putExtra("day", day);
            intent2.putExtra("category", category);
            startActivity(intent2);
        }
    }


}