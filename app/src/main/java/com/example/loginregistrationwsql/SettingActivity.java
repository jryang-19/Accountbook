package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {
    DatabaseHelper db;
    private static final String TAG = "SettingActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    String dateFrom;

    private TextView mDisplayDateTo;
    private DatePickerDialog.OnDateSetListener mDateSetListner2;
    String dateTo;

    Button setLimit;
    int expenseLimit;
    EditText expenseAmtInput;
    TextView tvDateInput;
    TextView tvDate2Input;
    TextView name123;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String result="";
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent_setting = getIntent();

        //Bundle b;
        //b =getIntent().getExtras(); //Important to have this in every page so that u can access ur data, it act as like a session storage
        //name = b.getString("name");   //Important to have this in every page so that u can access ur data, it act as like a session storage

        name123=(TextView)findViewById(R.id.name123);
        name123.setText("Name: " +name);
        db = new DatabaseHelper(this);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SettingActivity.this,
                        android.R.style.Theme_Translucent,
                        mDateSetListner,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + dayOfMonth);

                month = month + 1;
                dateFrom = year + "/" + month + "/" + dayOfMonth;
                mDisplayDate.setText(dateFrom);

            }
        };

        mDisplayDateTo = (TextView) findViewById(R.id.tvDate2);
        mDisplayDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SettingActivity.this,
                        android.R.style.Theme_Translucent,
                        mDateSetListner2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListner2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: dateTo: " + year + "/" + month + "/" + dayOfMonth);

                month = month + 1;
                dateTo = year + "/" + month + "/" + dayOfMonth;
                mDisplayDateTo.setText(dateTo);

            }
        };

        tvDateInput = (TextView) findViewById(R.id.tvDate);
        tvDate2Input = (TextView) findViewById(R.id.tvDate2);

        //When Set limit Button is pressed
        expenseAmtInput = (EditText) findViewById(R.id.expenseAmt);
        setLimit = (Button) findViewById(R.id.limitBtn);
        setLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(expenseAmtInput.length()==0){
                    expenseAmtInput.setError("Enter Amount Please!");
                }else if(tvDateInput.length()==0){
                    tvDateInput.setError("Enter Date Please!");
                }else if(tvDate2Input.length()==0){
                    tvDate2Input.setError("Enter Date Please!");
                }else{
                    expenseLimit = Integer.valueOf(expenseAmtInput.getText().toString());

                    //display the number in the little pop up

                    builder = new AlertDialog.Builder(SettingActivity.this);
                    builder.setTitle("Are you Sure?")
                            .setMessage("Set Expense limit to " + expenseLimit + " from " + dateFrom + " to " + dateTo + "?");



                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean isUpdated =db.updateLimit(name,expenseLimit,dateFrom,dateTo);
                            if(isUpdated==true){
                                showToast(String.valueOf(expenseLimit));
                                Intent i = new Intent(SettingActivity.this,SettingActivity.class);
                                i.putExtra("name",name);  //Important to have this in every page so that u can access ur data, it act as like a session storage
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(), "updated database fail", Toast.LENGTH_SHORT).show();
                            }



                        }

                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(SettingActivity.this, text, Toast.LENGTH_SHORT).show();

    }
    public void onClick_setting(View v){
        Intent intent_settingAct = new Intent(getApplicationContext(), SettingActivity.class);
        try{
            startActivity(intent_settingAct);
        }catch (Exception e){
            Log.d("ERROR", e.toString());
        }
        finally {
            finish();
        }
    }

    public void onClick_calendar(View v){
        Intent intent_calendarAct = new Intent(getApplicationContext(), CalendarActivity.class);
        try{
            startActivity(intent_calendarAct);
        }catch (Exception e){
            Log.d("ERROR", e.toString());
        }
        finally {
            finish();
        }
    }

    public void onClick_summary(View v){
        Intent intent_summaryAct = new Intent(getApplicationContext(), SummaryActivity.class);

        try{
            startActivity(intent_summaryAct);
        }catch (Exception e){
            Log.d("ERROR", e.toString());
        }
        finally {
            finish();
        }
    }
}