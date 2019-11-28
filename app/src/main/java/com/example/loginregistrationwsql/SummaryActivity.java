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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        chart1 = (PieChart) findViewById(R.id.tab1_chart_1);
        chart2 = (PieChart) findViewById(R.id.tab1_chart_2);
        chart3 = (BarChart) findViewById(R.id.tab1_chart_3);
        // 파이 차트 설정
// private void setPieChart(List<itemModel> itemList) {

//day
        chart1.clearChart();
        chart1.addPieSlice(new PieModel("food", 60, Color.parseColor("#CDA67F")));
        chart1.addPieSlice(new PieModel( "game",40, Color.parseColor("#00BFFF")));
        chart1.addPieSlice(new PieModel("cart",30, Color.parseColor("#181907")));
        chart1.startAnimation();
        // }
//month
        chart2.clearChart();
        chart2.addPieSlice(new PieModel("food", 50, Color.parseColor("#CDA67F")));
        chart2.addPieSlice(new PieModel("cart", 50, Color.parseColor("#00BFFF")));
        chart2.startAnimation();


// 막대 차트 설정
//private void setBarChart(List<itemModel> itemList2) {

        chart3.clearChart();

//chart3.addBar(new BarModel("12", 10f, 0xFF56B7F1));
//chart3.addBar(new BarModel("13", 10f, 0xFF56B7F1));
//chart3.addBar(new BarModel("14", 10f, 0xFF56B7F1));
        chart3.addBar(new BarModel("15", 20f, 0xFF56B7F1));
        chart3.addBar(new BarModel("16", 10f, 0xFF56B7F1));
        chart3.addBar(new BarModel("17", 10f, 0xFF56B7F1));

        chart3.startAnimation();
    }
    public void onClick_setting(View v){
        Intent intent_settingAct = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent_settingAct);
    }

    public void onClick_calendar(View v){
        Intent intent_calendarAct = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent_calendarAct);
    }

    public void onClick_summary(View v){
        Intent intent_summaryAct = new Intent(getApplicationContext(), SummaryActivity.class);
        startActivity(intent_summaryAct);
    }
}
