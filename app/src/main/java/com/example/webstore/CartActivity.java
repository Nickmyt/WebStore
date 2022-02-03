package com.example.webstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private Button BuyButton;
    private TextView priceText, posoText;
    private RecyclerView recyclerView2;
    private ArrayList<String> titleslist, desclist, linklist;
    private ArrayList<Long> pricelist , countlist;
    private ArrayList<CartModel> objlist;
    private double sum = 0;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        posoText = findViewById(R.id.poso);
        BuyButton = findViewById(R.id.button2);
        priceText = findViewById(R.id.Price);
        recyclerView2 = findViewById(R.id.recyclerView2);
        titleslist = new ArrayList<>();
        desclist = new ArrayList<>();
        linklist = new ArrayList<>();
        pricelist = new ArrayList<>();


        recyclerView2.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        objlist = (ArrayList<CartModel>)getIntent().getSerializableExtra("products");
        objlist.forEach((n) -> Decon(n));
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this,titleslist,desclist,linklist,pricelist, countlist);
        recyclerView2.setAdapter(cartAdapter);


    }

    private void Decon(CartModel n) {
        //Put the values in the Lists to fill the Adapter
        // Log.d("TEST",n.getTitle());
        if (titleslist.contains(n.getTitle())){
            count+=1;

        }else{
            titleslist.add(n.getTitle());
            desclist.add(n.getDesc());
            linklist.add(n.getTitle());
            pricelist.add(n.getPrice());
            sum += n.getPrice();
            priceText.setText(sum+" €");
        }







    }
}