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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {


    Context context;
    Button BuyButton;



    ArrayList<String> titlebook;
    ArrayList<String> descbook ;
    ArrayList<String> link;
    ArrayList<Long> pricebook ;
    ArrayList<Long> availbook ;

    public CartAdapter(Context context, ArrayList<String> titles, ArrayList<String> desc, ArrayList<String> link, ArrayList<Long> price, ArrayList<Long> avail  ){
        this.context = context;
        titlebook = titles;
        descbook = desc;
        this.link = link;
        pricebook = price;
        availbook = avail;

    }


    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =  layoutInflater.inflate(R.layout.my_row_cart,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

        holder.TextTitle.setText(titlebook.get(position));
        holder.TextDesc.setText(descbook.get(position));
        String text =((pricebook.get(position)))+" €";
        holder.priceText.setText(text);
        Picasso.with(context).load(link.get(position)).into(holder.imageViewCover);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView priceAllText , TextDesc , TextTitle , priceText;
        ImageView imageViewCover;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TextTitle = itemView.findViewById(R.id.titleText);
            TextDesc = itemView.findViewById(R.id.descText);
            imageViewCover = itemView.findViewById(R.id.CoverView);
            priceAllText = itemView.findViewById(R.id.textView14);



        }
    }
}
