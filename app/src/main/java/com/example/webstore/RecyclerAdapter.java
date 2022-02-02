package com.example.webstore;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    ArrayList<String> titlebook;
    ArrayList<String> descbook ;
    ArrayList<String> link;
    ArrayList<Long> pricebook ;
    ArrayList<Long> availbook ;
    Context context;
    private OnItemClickListener Mlistener;




    public RecyclerAdapter(Context ct,
                           ArrayList<String> titles,
                           ArrayList<String> desc,
                           ArrayList<String> link,
                           ArrayList<Long> price,
                           ArrayList<Long> avail
                           ) {
        context = ct;
        titlebook = titles;
        descbook = desc;
        this.link = link;
        pricebook = price;
        availbook = avail;

    }

    public interface OnItemClickListener{
        public void onItemClick(int position);
        public void TTs(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){ Mlistener = listener;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view  =  layoutInflater.inflate(R.layout.my_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view , Mlistener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.TitleText.setText(titlebook.get(position));
        holder.AvailText.setText(String.valueOf(availbook.get(position)));
        holder.DescText.setText(descbook.get(position));
        String text = String.valueOf((pricebook.get(position)))+"€";
        holder.PriceText.setText(text);




        Log.d("HERE",link.get(position) );

        Picasso.with(context).load(link.get(position)).into(holder.cover);



    }

    @Override
    public int getItemCount() {
        return availbook.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        TextView TitleText , DescText , PriceText, AvailText;
        ImageView cover;
        Button Buy;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            TitleText = itemView.findViewById(R.id.booktitle);
            DescText = itemView.findViewById(R.id.bookdesc);
            cover = itemView.findViewById(R.id.imageView2);
            PriceText = itemView.findViewById(R.id.bookprice);
            imageView = itemView.findViewById(R.id.imageView7);
            AvailText = itemView.findViewById(R.id.bookavail);
            Buy = itemView.findViewById(R.id.BuyButton);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAbsoluteAdapterPosition();
                        listener.TTs(position);

                    }
                }
            });
        }


    }
    public interface RecyclerviewClicklistener{
        public void onClick(View v , int position);

    }

}
