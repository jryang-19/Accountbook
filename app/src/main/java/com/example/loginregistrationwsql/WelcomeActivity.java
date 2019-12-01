package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView name123,PW;
    Button limit, logoutBtn;
    String displayLimit;
    String name;
    int userExpense;
    int userLimit;
    boolean setLimitToZero;

    int expenseTest = 0;

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
        // email을 특정 지목함. (name = email)

        name123.setText("Hello " +name);
        String passwor = db.displayPW(name);
        displayLimit = db.displayLimit(name);
        userExpense =db.retrieveExpense(name);
        PW.setText("Your password is " + passwor + ", your limit is " + displayLimit + ". Your expense is $" + userExpense);

        userLimit =Integer.valueOf(displayLimit);
        //CHECK IF USER LIMIT HAS EXCEEDED EXPENSE
        if(userLimit > userExpense){
            new AlertDialog.Builder(WelcomeActivity.this)
                    .setTitle("Alert")
                    .setMessage("You have exceeded your limit!")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLimitToZero =db.setLimitToZero(name,0);
                            if(setLimitToZero==true){
                                Toast.makeText(getApplicationContext(), "Limit Reset to 0, Pleace set limit again", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "updated database fail", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .show();
        }

        limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this,SettingActivity.class);
                i.putExtra("name",name);  //Important to have this in every page so that u can access ur data, it act as like a session storage
                startActivity(i);
            }
        });


        logoutBtn = (Button)findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
