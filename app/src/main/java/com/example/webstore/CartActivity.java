package com.example.webstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private Button BuyButton;
    private TextView priceText;
    private RecyclerView recyclerView2;
    private ArrayList<String> titleslist, desclist, linklist;
    private ArrayList<Long> pricelist;
    private ArrayList<Object> objlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        BuyButton = findViewById(R.id.button2);
        priceText = findViewById(R.id.Price);
        recyclerView2 = findViewById(R.id.recyclerView2);
        titleslist = new ArrayList<>();
        desclist = new ArrayList<>();
        linklist = new ArrayList<>();
        pricelist = new ArrayList<>();


        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        objlist = (ArrayList<Object>)getIntent().getSerializableExtra("products");
        objlist.forEach((n) -> Decon(n));
        //CartAdapter cartAdapter = new CartAdapter();


    }

    private void Decon(Object n) {





    }
}