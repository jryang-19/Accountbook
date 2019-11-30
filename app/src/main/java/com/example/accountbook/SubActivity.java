package com.example.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;

public class SubActivity extends AppCompatActivity {
    ImageView ImageView_add;
    TextView sub_day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent_sub = getIntent();

        ////////////////////////////////////////
        sub_day = findViewById(R.id.sub_day);
        Bundle bundle = intent_sub.getExtras();
        int year = bundle.getInt("year");
        int month = bundle.getInt("month");
        int date = bundle.getInt("date");
        sub_day.setText(year+" - "+month+" - "+date);
        ///////////////////////////////////////
        ImageView_add =  findViewById(R.id.ImageView_add);
        ImageView_add.setClickable(true);
        ImageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity .this , CategoriesActivity.class);

                //intent.putExtra("year", year);
                startActivity(intent);
                finish();
            }
        });
    }
}