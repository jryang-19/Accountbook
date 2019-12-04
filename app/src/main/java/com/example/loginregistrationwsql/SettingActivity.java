package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.StringTokenizer;

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
    Button deletLimit;
    int expenseLimit;
    EditText expenseAmtInput;
    TextView tvDateInput;
    TextView tvDate2Input;
    TextView limit_set;
    AlertDialog dialog;
    AlertDialog.Builder builder_set;
    String name;
    String PW;
    String show_limit;
    String push_title;
    String push_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent_setting = getIntent();

        Bundle b = intent_setting.getExtras();
        name = b.getString("name");   //Important to have this in every page so that u can access ur data, it act as like a session storage
        PW = b.getString("PW");

        //name123=(TextView)findViewById(R.id.name123);
        //name123.setText("Name: " +name);
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

                    builder_set = new AlertDialog.Builder(SettingActivity.this);
                    builder_set.setTitle("Are you Sure?")
                            .setMessage("Set Expense limit to " + expenseLimit + " from " + dateFrom + " to " + dateTo + "?");



                    builder_set.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean isUpdated;
                            if(confirm_date(dateFrom, dateTo)) {
                                isUpdated = db.updateLimit(PW, expenseLimit, dateFrom, dateTo, name);
                                if (isUpdated == true) {
                                    showToast(String.valueOf(expenseLimit));
                                    Intent i = new Intent(SettingActivity.this, SettingActivity.class);
                                    i.putExtra("name", name); //Important to have this in every page so that u can access ur data, it act as like a session storage
                                    i.putExtra("PW", PW);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "updated database fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "check date", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SettingActivity.this, SettingActivity.class);
                                i.putExtra("name", name); //Important to have this in every page so that u can access ur data, it act as like a session storage
                                i.putExtra("PW", PW);
                                startActivity(i);
                                finish();
                            }
                        }

                    });
                    builder_set.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog = builder_set.create();
                    dialog.show();
                }
            }
        });

        deletLimit = (Button) findViewById(R.id.delete);
        deletLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateLimit(PW, 0, "", "", "");
                Toast.makeText(getApplicationContext(), "Limitation set deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SettingActivity.this, SettingActivity.class);
                i.putExtra("name",name);  //Important to have this in every page so that u can access ur data, it act as like a session storage
                i.putExtra("PW",PW);
                startActivity(i);
                finish();
            }

        });

        limit_set = (TextView) findViewById(R.id.limit_set);
        show_limit = db.showLimit(name);
        limit_set.setText(show_limit);

        //푸시 알림을 보내기위해 시스템에 권한을 요청하여 생성

        final NotificationManager notificationManager =

                (NotificationManager)SettingActivity.this.getSystemService(SettingActivity.this.NOTIFICATION_SERVICE);



    //푸시 알림 터치시 실행할 작업 설정(여기선 MainActivity로 이동하도록 설정)

        final Intent intent = new Intent(SettingActivity.this.getApplicationContext(), SettingActivity.class);

        //Notification 객체 생성
        final Notification.Builder builder_alert = new Notification.Builder(getApplicationContext());



//푸시 알림을 터치하여 실행할 작업에 대한 Flag 설정 (현재 액티비티를 최상단으로 올린다 | 최상단 액티비티를 제외하고 모든 액티비티를 제거한다)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean set_valid = db.checkLimit(name);
                if (set_valid) {
                    push_title = db.push_title(name);
                    push_text = db.push_text(name);
                    //푸시 알림을 보내기위해 시스템에 권한을 요청하여 생성
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        NotificationChannel notificationChannel = new NotificationChannel("alarm_channel", "test", NotificationManager.IMPORTANCE_DEFAULT);
                        notificationChannel.setDescription("test");
                        notificationManager.createNotificationChannel(notificationChannel);
                    }

                    Notification noti = new NotificationCompat.Builder(SettingActivity.this, "alarm_channel")
                            .setColor(0)
                            .setDefaults(Notification.DEFAULT_LIGHTS)
                            .setSmallIcon(R.drawable.alarm)
                            .setContentTitle(push_title)
                            .setContentText(push_text)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

                    final NotificationManager nm =

                            (NotificationManager) SettingActivity.this.getSystemService(SettingActivity.this.NOTIFICATION_SERVICE);

                    nm.notify(NOTIFICATION_SERVICE, 0, noti);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No limit setting", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(SettingActivity.this, text, Toast.LENGTH_SHORT).show();

    }

    private Boolean confirm_date(String datefrom, String dateto){
        StringTokenizer st1 = new StringTokenizer(datefrom, "/");
        StringTokenizer st2 = new StringTokenizer(dateto, "/");

        int from = 0;
        int to = 0;

        while(st1.hasMoreTokens()){
            from += Integer.parseInt(st1.nextToken());
        }
        while(st2.hasMoreTokens()){
            to += Integer.parseInt(st2.nextToken());
        }

        if(from < to)
            return true;
        else
            return false;
    }
}