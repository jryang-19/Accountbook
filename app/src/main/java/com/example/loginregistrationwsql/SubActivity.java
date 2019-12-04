package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter; //adapter
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Data> arrayList;
    DatabaseHelper db;
    public static Activity subact;

    ImageView ImageView_add; //add image
    TextView date;
    EditText input;
    Bundle bun;
    String name;
    int year;
    int month;
    int day;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        subact = SubActivity.this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        Intent intent_sub = getIntent();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view); //id 연결
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //Data객체에 담아 어레이 리스트로 어댑터로 보냄.

        bun = intent_sub.getExtras();
        year = bun.getInt("year");
        month = bun.getInt("month");
        day = bun.getInt("day");
        name = bun.getString("name");
        date = (TextView)findViewById(R.id.date);
        date.setText("" + year + " - " + month + " - " + day);

        db = new DatabaseHelper(this); //sqlite 연동

        int n = db.day_count(name, year, month, day);
        for(int a=1; a<n+1; a++) {
            Data data = new Data();
            data.setImage_id(db.image_id(name, year, month, day, a));
            data.setExpense(db.expanse(name, year, month, day, a));
            data.setInfo(db.info(name, year, month, day, a));
            arrayList.add(data);
        }
        mAdapter = new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(mAdapter);

        ImageView_add = findViewById(R.id.ImageView_add);
        ImageView_add.setClickable(true);
        ImageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity .this , CategoriesActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    public void onClick_delete(View v){
        AlertDialog.Builder builder_set;
        AlertDialog dialog;

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        input = new EditText(this);

        builder1.setTitle("Select position");
        builder1.setView(input);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                position = Integer.parseInt(input.getText().toString());
                boolean delete = db.deleteExpanse(name, year, month, day, position);
                if(position <= db.day_count(name, year, month, day)) {
                    Toast.makeText(getApplicationContext(), "Deleted expanse", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SubActivity.this, SubActivity.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("day", day);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SubActivity.this, SubActivity.class);
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("day", day);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
            }
        });
        builder1.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                position = 0;
            }
        });
        AlertDialog alert = builder1.create();
        alert.show();

        builder_set = new AlertDialog.Builder(SubActivity.this);
        builder_set.setTitle("Are you Sure?")
                .setMessage("Delete expanse?");

        builder_set.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        builder_set.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SubActivity .this , SubActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        dialog = builder_set.create();
        dialog.show();
    }
}
