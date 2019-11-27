package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Intent intent = getIntent();

        findViewById(R.id.ImageView_dinner).setOnClickListener(this);
        findViewById(R.id.ImageView_cafe).setOnClickListener(this);
        findViewById(R.id.ImageView_cart).setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        int resourceId = 0;
        switch (v.getId()) {
            case R.id.ImageView_dinner:
                resourceId = R.drawable.food;
                break;
            case R.id.ImageView_cafe:
                resourceId = R.drawable.cafe;
                break;
            case R.id.ImageView_cart:
                resourceId = R.drawable.cart;
                break;
        }
        if (resourceId > 0){
            Intent intent2 = new Intent(CategoriesActivity.this,CountActivity.class);
            intent2.putExtra("resourceId",resourceId);
            startActivity(intent2);
        }
    }


}
