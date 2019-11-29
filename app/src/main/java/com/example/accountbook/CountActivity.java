package com.example.accountbook;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CountActivity extends AppCompatActivity {
Button button_expanse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_count);
        Intent intent2 = getIntent();
        button_expanse = findViewById(R.id.button_expanse);

        int resourceId = getIntent().getIntExtra("resourceId",0);
        if (resourceId > 0) {
            ((ImageView) findViewById(R.id.photo)).setImageResource(resourceId);
        }

        button_expanse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosub = new Intent(CountActivity.this,SubActivity.class);
                startActivity(gotosub);
            }
        });
    }

}