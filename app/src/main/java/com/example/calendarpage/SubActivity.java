package com.example.calendarpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView dateBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent_subAct = getIntent();
        int year = intent_subAct.getExtras().getInt("year");
        int month = intent_subAct.getExtras().getInt("month");
        int date = intent_subAct.getExtras().getInt("date");

        dateBar = (TextView)findViewById(R.id.date_bar);

        dateBar.setText(year + "Y "+month +"M "+ date+"D ");

        //dateBar.setText("asdfsdaf");
    }
}
