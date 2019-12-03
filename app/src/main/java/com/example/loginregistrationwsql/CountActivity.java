package com.example.loginregistrationwsql;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CountActivity extends AppCompatActivity {
    String name;
    DatabaseHelper db;
    Button b1;
    EditText e;
    Bundle bun;
    int year;
    int month;
    int day;
    int price;
    int category;
    int num_day;

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        db = new DatabaseHelper(this);
        Bundle b =getIntent().getExtras(); //Important to have this in every page so that u can access ur data, it act as like a session storage
        name = b.getString("name"); //Important to have this in every page so that u can access ur data, it act as like a session storage

        Intent intent2 = getIntent();
        bun = intent2.getExtras();
        year = bun.getInt("year");
        month = bun.getInt("month");
        day = bun.getInt("day");
        category = bun.getInt("category");
        name = bun.getString("name");
        num_day = db.num_day(name, year, month, day);

        final int resourceId = getIntent().getIntExtra("resourceId",0);
        if (resourceId > 0) {
            ((ImageView) findViewById(R.id.photo)).setImageResource(resourceId);
        }

        b1 = (Button)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    e = (EditText) findViewById(R.id.how_much);
                    price = Integer.parseInt(e.getText().toString().trim());
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CountActivity.this, CalendarActivity.class);
                    i.putExtra("name", name);
                    startActivity(i);
                }

                Intent i = new Intent(CountActivity.this, CalendarActivity.class);
                i.putExtra("name", name);
                db.insert(name, "",0,"","", "",  year, month, day, price, category, resourceId, num_day+1);
                startActivity(i);
            }
        });
    }
}