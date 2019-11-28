package com.example.accountbook;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        Intent intent2 = getIntent();

        int resourceId = getIntent().getIntExtra("resourceId",0);
        if (resourceId > 0) {
            ((ImageView) findViewById(R.id.photo)).setImageResource(resourceId);
        }

    }
}