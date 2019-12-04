package com.example.loginregistrationwsql;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Intent intent = getIntent();

        bun = intent.getExtras();
        year = bun.getInt("year");
        month = bun.getInt("month");
        day = bun.getInt("day");
        name = bun.getString("name");
        PW = bun.getString("PW");

        findViewById(R.id.ImageView_dinner).setOnClickListener(this);
        findViewById(R.id.ImageView_cafe).setOnClickListener(this);
        findViewById(R.id.ImageView_cart).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        int resourceId = 0;
        switch ((v.getId())) {
            case R.id.ImageView_dinner:
                resourceId = R.drawable.food;
                category = 1;
                break;
            case R.id.ImageView_cafe:
                resourceId = R.drawable.cafe;
                category = 2;
                break;
            case R.id.ImageView_cart:
                resourceId = R.drawable.cart;
                category = 3;
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