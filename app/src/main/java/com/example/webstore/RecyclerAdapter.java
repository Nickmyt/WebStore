package com.example.webstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    ArrayList<String> titlebook;
    ArrayList<String> descbook ;
    ArrayList<String> link;
    ArrayList<Long> pricebook ;
    ArrayList<Long> availbook ;
    Context context;

    public RecyclerAdapter(Context ct,
                           ArrayList<String> titles,
                           ArrayList<String> desc,
                           ArrayList<String> link,
                           ArrayList<Long> price,
                           ArrayList<Long> avail) {
        context = ct;
        titlebook = titles;
        descbook = desc;
        this.link = link;
        pricebook = price;
        availbook = avail;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view  =  layoutInflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TitleText.setText(titlebook.get(position));
        holder.AvailText.setText(String.valueOf(availbook.get(position)));
        holder.DescText.setText(descbook.get(position));
        holder.PriceText.setText(String.valueOf((pricebook.get(position))));

    }

    @Override
    public int getItemCount() {
        return availbook.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView TitleText , DescText , PriceText, AvailText;
        ImageView cover;
        Button Buy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleText = itemView.findViewById(R.id.booktitle);
            DescText = itemView.findViewById(R.id.bookdesc);
            cover = itemView.findViewById(R.id.imageView2);
            PriceText = itemView.findViewById(R.id.bookprice);
            AvailText = itemView.findViewById(R.id.bookavail);
            Buy = itemView.findViewById(R.id.BuyButton);
        }
    }


}