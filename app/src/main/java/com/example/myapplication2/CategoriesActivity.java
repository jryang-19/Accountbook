package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {
    ImageView ImageView_dinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ImageView_dinner = findViewById(R.id.ImageView_dinner);
        ImageView_dinner.setClickable(true);
        ImageView_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(CategoriesActivity.this, CountActivity.class);
                startActivity(intent2);
            }
        });
        Intent intent = getIntent();

    }
}
