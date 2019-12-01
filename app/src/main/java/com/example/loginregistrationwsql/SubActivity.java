package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;

public class SubActivity extends AppCompatActivity {
    ImageView ImageView_add;
    TextView date;
    Bundle bun;
    String name;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent_sub = getIntent();

        bun = intent_sub.getExtras();
        year = bun.getInt("year");
        month = bun.getInt("month");
        day = bun.getInt("day");
        name = bun.getString("name");
        date = (TextView)findViewById(R.id.date);
        date.setText("" + year + " - " + month + " - " + day);


        ImageView_add =  findViewById(R.id.ImageView_add);
        ImageView_add.setClickable(true);
        ImageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity .this , CategoriesActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
