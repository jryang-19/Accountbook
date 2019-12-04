package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3; //email = e1 , pass = e2 , repass = e3.
    Button b1,b2,b3; // b1 = register button , b2 = login
    public static Activity regact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        regact = MainActivity.this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.pass);
        e3 = (EditText)findViewById(R.id.cpass);
        b1 = (Button)findViewById(R.id.register);
        b2 = (Button)findViewById(R.id.login);
        b3 = (Button)findViewById(R.id.delete_user);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 =e1.getText().toString(); //email
                String s2 =e2.getText().toString(); //password
                String s3 =e3.getText().toString(); //confirm password
                String s4 ="Current Empty";//Monthly expenses // no meaning
                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                else if(s2.equals(s3)){
                    Boolean chkemail = db.chkemail(s1);
                    Boolean Chkemailpass = db.emailPassword(s1,s2);
                    if(chkemail == true){
                        Toast.makeText(getApplicationContext(), "Email Not exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Chkemailpass) {
                            Boolean delete = db.deleteUser(s1);
                            if (delete) {
                                Toast.makeText(getApplicationContext(), "Deleted User " + s1 + " Successfully", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Delete User failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Wrong ID or Password", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(MainActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 =e1.getText().toString(); //email
                String s2 =e2.getText().toString(); //password
                String s3 =e3.getText().toString(); //confirm password
                String s4 ="Current Empty";//Monthly expenses // no meaning
                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                    else if(s2.equals(s3)){
                        Boolean chkemail = db.chkemail(s1);
                        if(chkemail == true){
                            Boolean insert = db.insert(s1,s2,0,"","", "", 0, 0, 0, 0, 0, 0, 0, "");
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent(MainActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
