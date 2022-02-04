package com.example.webstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CartActivity extends AppCompatActivity {
    private Button BuyButton;
    private TextView priceText, posoText;
    private RecyclerView recyclerView2;
    private ArrayList<String> titleslist, desclist, linklist;
    private ArrayList<Long> pricelist , countlist;
    private ArrayList<CartModel> objlist;
    private ProgressBar progressBar;
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
        progressBar = findViewById(R.id.progressBar3);


        recyclerView2.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        objlist = (ArrayList<CartModel>)getIntent().getSerializableExtra("products");
        //Populate Adapter
        if(objlist.isEmpty()){
            titleslist.add("Empty");
            desclist.add(" ");
            linklist.add(" ");
            pricelist.add((long) 0.00000);
            countlist.add((long) 0.00000);
            CartAdapter cartAdapter = new CartAdapter(CartActivity.this,titleslist,desclist,linklist,pricelist, countlist);
        }else{
            objlist.forEach((n) -> Decon(n));
            CartAdapter cartAdapter = new CartAdapter(CartActivity.this,titleslist,desclist,linklist,pricelist, countlist);
            recyclerView2.setAdapter(cartAdapter);
        }



        BuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                objlist.removeAll(objlist);
                Toast.makeText(CartActivity.this, "Order Completed Successfully", Toast.LENGTH_SHORT);


                startActivity(new Intent(CartActivity.this, Order.class));
                finish();




            }
        });

    }
    //Populate the arraylists and get the final price of the order
    private void Decon(CartModel n) {
        //Put the values in the Lists to fill the Adapter
        // Log.d("TEST",n.getTitle());

            titleslist.add(n.getTitle());
            desclist.add(n.getDesc());
            linklist.add(n.getTitle());
            pricelist.add(n.getPrice());
            sum += n.getPrice();
            priceText.setText(sum+" €");



    }




}