package com.example.webstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Bookstore extends AppCompatActivity {
    private TextView textView;
    private String user;
    private Button buttonbuy;
    private FirebaseAuth mAuth;
    private FirebaseUser userfire;
    private FirebaseFirestore db;
    RecyclerView recyclerView;
    int booknum = 0;

    //Query Variables


    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<String> imagelink= new ArrayList<String>();
    ArrayList<Long> price = new ArrayList<>();
    ArrayList<Long> avail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

        textView = findViewById(R.id.user);
        textView.setText(getIntent().getStringExtra("email"));
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Bookstore.this));

        db.collection("Books").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        title.add(document.getId());
                        desc.add(document.getString("Description"));
                        imagelink.add(document.getString("Link"));
                        price.add(document.getLong("Price"));
                        avail.add(document.getLong("Availability"));
                        //TEST to see if the values are fetched properly
                        Log.d("Test", String.valueOf(title));
                        Log.d("Test", String.valueOf(desc));
                        Log.d("Test", String.valueOf(imagelink));
                        Log.d("Test", String.valueOf(price));
                        Log.d("Test", String.valueOf(avail));




                    }
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(Bookstore.this,title,desc,imagelink,price,avail);
                    try{
                        //Initialize the adapter with the recyclerview object

                        recyclerView.setAdapter(recyclerAdapter);


                    }
                    catch (Exception e){
                        Toast.makeText(Bookstore.this, "Could not load The Books", Toast.LENGTH_SHORT).show();
                        Log.d("What Happened : ", String.valueOf(e));
                    }
                }else{
                    Toast.makeText(Bookstore.this, "Unavailable , Data Connection Error", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void SignOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public void Buy(View view){
        buttonbuy = findViewById(R.id.BuyButton);



    }

}