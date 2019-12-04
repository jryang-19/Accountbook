package com.example.loginregistrationwsql;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

public class SummaryActivity extends AppCompatActivity {

    PieChart chart1;
    PieChart chart2;
    BarChart chart3;
    String name;
    String PW;
    DatabaseHelper db;
    int year;
    int pre_year;
    int month;
    int pre_month;
    int pre_month2;
    int day;
    String s_month;
    String s_pre_month;
    String s_pre_month2;
    int[] day_category = new int[4];
    int[] month_category = new int[4];

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        db = new DatabaseHelper(this);

        Intent intent_summary = getIntent();

        Bundle bun;
        bun = intent_summary.getExtras();
        year = bun.getInt("year");
        month = bun.getInt("month");
        day = bun.getInt("day");
        name = bun.getString("name");
        PW = bun.getString("PW");

        pre_year = year;
        pre_month = month-1;
        pre_month2 = month-2;
        if(month == 1) {
            pre_month = 12;
            pre_year = pre_year - 1;
        }
        else if(month == 2){
            pre_month2 = 12;
            pre_year = pre_year - 1;
        }

        s_month = ""+month;
        s_pre_month = ""+pre_month;
        s_pre_month2 = ""+pre_month2;


        chart1 = (PieChart) findViewById(R.id.tab1_chart_1);
        chart2 = (PieChart) findViewById(R.id.tab1_chart_2);
        chart3 = (BarChart) findViewById(R.id.tab1_chart_3);
        // 파이 차트 설정
// private void setPieChart(List<itemModel> itemList) {

//day
        chart1.clearChart();
        day_category = db.day_expanse_category(name, year, month, day);
        chart1.addPieSlice(new PieModel("Food", day_category[1], Color.parseColor("#FF0000")));
        chart1.addPieSlice(new PieModel("Drink",day_category[2], Color.parseColor("#FF8000")));
        chart1.addPieSlice(new PieModel("Transportation",day_category[3], Color.parseColor("#FFFF00")));
        chart1.addPieSlice(new PieModel("Gift",day_category[4], Color.parseColor("#80FF00")));
        chart1.addPieSlice(new PieModel("Clothes",day_category[5], Color.parseColor("#00FFBF")));
        chart1.addPieSlice(new PieModel("Fees",day_category[6], Color.parseColor("#0080FF")));
        chart1.addPieSlice(new PieModel("Education",day_category[7], Color.parseColor("#8000FF")));
        chart1.addPieSlice(new PieModel("Entertainment",day_category[8], Color.parseColor("#FF00FF")));
        chart1.addPieSlice(new PieModel("Groceries",day_category[9], Color.parseColor("#3B0B17")));
        chart1.addPieSlice(new PieModel("Health",day_category[10], Color.parseColor("#A4A4A4")));
        chart1.addPieSlice(new PieModel("Beauty",day_category[11], Color.parseColor("#000000")));
        chart1.addPieSlice(new PieModel("Others",day_category[12], Color.parseColor("#3A2F0B")));
        chart1.startAnimation();
        // }
//month
        chart2.clearChart();
        month_category = db.month_expanse_category(name, year, month);
        chart2.addPieSlice(new PieModel("Food", month_category[1], Color.parseColor("#FF0000")));
        chart2.addPieSlice(new PieModel("Drink",month_category[2], Color.parseColor("#FF8000")));
        chart2.addPieSlice(new PieModel("Transportation",month_category[3], Color.parseColor("#FFFF00")));
        chart2.addPieSlice(new PieModel("Gift",month_category[4], Color.parseColor("#80FF00")));
        chart2.addPieSlice(new PieModel("Clothes",month_category[5], Color.parseColor("#00FFBF")));
        chart2.addPieSlice(new PieModel("Fees",month_category[6], Color.parseColor("#0080FF")));
        chart2.addPieSlice(new PieModel("Education",month_category[7], Color.parseColor("#8000FF")));
        chart2.addPieSlice(new PieModel("Entertainment",month_category[8], Color.parseColor("#FF00FF")));
        chart2.addPieSlice(new PieModel("Groceries",month_category[9], Color.parseColor("#3B0B17")));
        chart2.addPieSlice(new PieModel("Health",month_category[10], Color.parseColor("#A4A4A4")));
        chart2.addPieSlice(new PieModel("Beauty",month_category[11], Color.parseColor("#000000")));
        chart2.addPieSlice(new PieModel("Others",month_category[12], Color.parseColor("#3A2F0B")));
        chart2.startAnimation();


// 막대 차트 설정
//private void setBarChart(List<itemModel> itemList2) {

        chart3.clearChart();

//chart3.addBar(new BarModel("12", 10f, 0xFF56B7F1));
//chart3.addBar(new BarModel("13", 10f, 0xFF56B7F1));
//chart3.addBar(new BarModel("14", 10f, 0xFF56B7F1));
        chart3.addBar(new BarModel(s_pre_month2, db.month_expanse(name, pre_year, pre_month2), 0xFF56B7F1));
        chart3.addBar(new BarModel(s_pre_month, db.month_expanse(name, pre_year, pre_month), 0xFF56B7F1));
        chart3.addBar(new BarModel(s_month, db.month_expanse(name, year, month), 0xFF56B7F1));

        chart3.startAnimation();
    }
}

