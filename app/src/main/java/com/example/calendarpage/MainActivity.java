package com.example.calendarpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
//** import android.view.View.OnClickListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView topBar;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;
    private int empty_day;

    private class calMonth{
        int year;
        int month;

        public calMonth(int y, int m) {
            this.year = y;
            this.month = m;
        }
    }

    public calMonth calMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topBar = (TextView)findViewById(R.id.top_bar);
        gridView = (GridView)findViewById(R.id.gridview);

        mCal = Calendar.getInstance();

        Date today_date = getNowDate();

        calMonth= new calMonth(today_date.getYear()+1900,today_date.getMonth()+1);

        printCal(calMonth);
    }

    private void printCal(calMonth calMonth ){
        makeMonthlyCal(calMonth);
        topBar.setText(calMonth.year+" / " + calMonth.month);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);
    }

    private void makeMonthlyCal(calMonth calMonth ){
        mCal.set(calMonth.year,calMonth.month-1,1);
        empty_day = mCal.get(Calendar.DAY_OF_WEEK);

        dayList = new ArrayList<String>();
        dayList.add("일"); // day_num 0
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토"); // day_num 6
        // dat_num 7 : day_names

        for (int i = 1; i < empty_day; i++) {
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);
    }

    private Date getNowDate(){
        long now = System.currentTimeMillis();
        final Date today_Date = new Date(now);

        return today_Date;
    }

    private int getDayNum(String date){
        int intDate=0;
        try{
            intDate = Integer.parseInt((date));
        }
        catch(NumberFormatException e){
            return 7;
        }
        int day_ = ((intDate-1)%7+empty_day-1)%7;
        return day_ != -1 ? day_ : 6 ;
    }

    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }
    }

    private class GridAdapter extends BaseAdapter {
        private final List<String> list;
        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));

            mCal = Calendar.getInstance();

            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);

            switch (getDayNum(getItem(position)))
            {
                case 0:
                    holder.tvItemGridView.setTextColor(getResources().getColor(R.color.color_red));
                    break;

                case 6:
                    holder.tvItemGridView.setTextColor(getResources().getColor(R.color.color_blue));
                    break;

                default:
                    holder.tvItemGridView.setTextColor(getResources().getColor(R.color.color_black));
            }

            holder.tvItemGridView.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_subAct = new Intent(getApplicationContext(), SubActivity.class);

                    intent_subAct.putExtra("year", calMonth.year);
                    intent_subAct.putExtra("month", calMonth.month);
                    intent_subAct.putExtra("date", Integer.parseInt(getItem(position)));

                    startActivity(intent_subAct);
                }
            });

            return convertView;
        }
    }

    private class ViewHolder{
        TextView tvItemGridView;
    }

    public void onClick_next(View v){
        if(calMonth.month ==12){
            calMonth.month =1;
            calMonth.year++;
        }
        else{
            calMonth.month++;
        }
        printCal(calMonth);
    }

    public void onClick_back(View v){
        if(calMonth.month ==1){
            calMonth.month =12;
            calMonth.year--;
        }
        else{
            calMonth.month--;
        }
        printCal(calMonth);
    }
}

/*
 * */
