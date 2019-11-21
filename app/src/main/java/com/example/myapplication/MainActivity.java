package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

public class MainActivity extends AppCompatActivity {

    PieChart chart1;
    PieChart chart2;
    BarChart chart3;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chart1 = (PieChart) findViewById(R.id.chart_day);
        chart2 = (PieChart) findViewById(R.id.chart_month);
        chart3 = (BarChart) findViewById(R.id.chart_last);

        //}


       //day
        chart1.clearChart();
        chart1.addPieSlice(new PieModel("food", 60, Color.parseColor("#CDA67F")));
        chart1.addPieSlice(new PieModel( "game",40, Color.parseColor("#00BFFF")));
        chart1.addPieSlice(new PieModel("cart",30, Color.parseColor("#181907")));
        chart1.startAnimation();

        //month
        chart2.clearChart();
        chart2.addPieSlice(new PieModel("food", 50, Color.parseColor("#CDA67F")));
        chart2.addPieSlice(new PieModel("cart", 50, Color.parseColor("#00BFFF")));
        chart2.startAnimation();


        // 막대 차트 설정
        chart3.clearChart();
        chart3.addBar(new BarModel("12", 100000, 0xFF56B7F1));
        chart3.addBar(new BarModel("13", 112234, 0xFF56B7F1));
        chart3.addBar(new BarModel("14", 1223345, 0xFF56B7F1));
        //chart3.addBar(new BarModel("10월", 20, 0x2ECCFA));
        //chart3.addBar(new BarModel("11월", 10, 0x00FFFF));
        //chart3.addBar(new BarModel("12월", 10, 0x013ADF));
        chart3.startAnimation();
    }
}