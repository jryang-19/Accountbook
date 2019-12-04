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
        chart1.addPieSlice(new PieModel("food", day_category[1], Color.parseColor("#CDA67F")));
        chart1.addPieSlice(new PieModel("game",day_category[2], Color.parseColor("#00BFFF")));
        chart1.addPieSlice(new PieModel("cart",day_category[3], Color.parseColor("#181907")));
        chart1.startAnimation();
        // }
//month
        chart2.clearChart();
        month_category = db.month_expanse_category(name, year, month);
        chart2.addPieSlice(new PieModel("food", month_category[1], Color.parseColor("#CDA67F")));
        chart2.addPieSlice(new PieModel("game", month_category[2], Color.parseColor("#00BFFF")));
        chart2.addPieSlice(new PieModel("cart", month_category[3], Color.parseColor("#181907")));
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
    public void onClick_setting(View v){
        Intent intent_settingAct = new Intent(getApplicationContext(), SettingActivity.class);
        intent_settingAct.putExtra("name", name);
        intent_settingAct.putExtra("year", year);
        intent_settingAct.putExtra("month", month);
        intent_settingAct.putExtra("day", day);
        intent_settingAct.putExtra("PW", PW);
        startActivity(intent_settingAct);
    }

    public void onClick_calendar(View v){
        Intent intent_calendAct = new Intent(getApplicationContext(), CalendarActivity.class);
        intent_calendAct.putExtra("name", name);
        intent_calendAct.putExtra("year", year);
        intent_calendAct.putExtra("month", month);
        intent_calendAct.putExtra("day", day);
        intent_calendAct.putExtra("PW", PW);
        startActivity(intent_calendAct);
    }

    public void onClick_summary(View v){
        Intent intent_summaryAct = new Intent(getApplicationContext(), SummaryActivity.class);
        intent_summaryAct.putExtra("name", name);
        intent_summaryAct.putExtra("year", year);
        intent_summaryAct.putExtra("month", month);
        intent_summaryAct.putExtra("day", day);
        intent_summaryAct.putExtra("PW", PW);
        startActivity(intent_summaryAct);
    }
}

