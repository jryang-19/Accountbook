package com.example.loginregistrationwsql;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView .Adapter<CustomAdapter.CustomViewHolder>{
    private ArrayList<Data> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    //최초의 뷰 홀더
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }
    //각 아이템들을 매칭해줌
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int i = arrayList.get(position).getImage_id();
        Glide.with(context).load("").placeholder(i).into(holder.mImageView);
        holder.Expanse_View.setText(String.valueOf(arrayList.get(position).getExpense()));
        holder.test.setText(arrayList.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView Expanse_View; //tv_pw
        TextView test;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.mImageView);
            this.Expanse_View = itemView.findViewById(R.id.Expanse_View);
            this.test = itemView.findViewById(R.id.test);
        }
    }
}