package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class WelcomeActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView name123,PW;
    Button limit;
    String displayLimit;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        name123=(TextView)findViewById(R.id.name123);
        PW = (TextView)findViewById(R.id.password);
        limit = (Button)findViewById(R.id.setLimit);

        Bundle b =getIntent().getExtras(); //Important to have this in every page so that u can access ur data, it act as like a session storage
        name = b.getString("name");   //Important to have this in every page so that u can access ur data, it act as like a session storage
        name123.setText("Hello " +name);
        String passwor = db.displayPW(name);
        displayLimit = db.displayLimit(name);
        PW.setText("Your password is " + passwor + ", your limit is " + displayLimit);


        limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this,SettingActivity.class);
                i.putExtra("name",name);  //Important to have this in every page so that u can access ur data, it act as like a session storage
                startActivity(i);
            }
        });



    }
}
