package com.example.loginregistrationwsql;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.time.Instant;

public class SubActivity extends AppCompatActivity {
    ImageView ImageView_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent_sub = getIntent();

        ImageView_add =  findViewById(R.id.ImageView_add);
        ImageView_add.setClickable(true);
        ImageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity .this , CategoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}