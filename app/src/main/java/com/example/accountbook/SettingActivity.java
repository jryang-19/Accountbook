package com.example.accountbook;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Date;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
    public void onClick_setting(View v){
        Intent intent_settingAct = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent_settingAct);
    }

    public void onClick_main(View v){
        Intent intent_mainAct = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_mainAct);
    }

    public void onClick_summary(View v){
        Intent intent_summaryAct = new Intent(getApplicationContext(), SummaryActivity.class);
        startActivity(intent_summaryAct);
    }
}

