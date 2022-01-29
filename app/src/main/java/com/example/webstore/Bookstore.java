package com.example.webstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private FirebaseAuth mAuth;
    private FirebaseUser userfire;
    private FirebaseFirestore db;
    RecyclerView recyclerView;

    //Query Variables


    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<String> imagelink= new ArrayList<String>();
    ArrayList price = new ArrayList<>();
    ArrayList avail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

        textView = findViewById(R.id.user);
        textView.setText(getIntent().getStringExtra("email"));
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                db.collection("Books").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                title.add(document.getId());
                                desc.add(document.getString("Description"));
                                imagelink.add(document.getString("Link"));
                                price.add(document.getDouble("Price"));
                                avail.add(document.getDouble("Availability"));
                                Log.d("Test", String.valueOf(title));
                                Log.d("Test", String.valueOf(desc));
                                Log.d("Test", String.valueOf(imagelink));
                                Log.d("Test", String.valueOf(price));
                                Log.d("Test", String.valueOf(avail));



                            }
                        }else{
                            Toast.makeText(Bookstore.this, "Unavailable , Data Connection Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this,title,desc,imagelink,price,avail);
        try{
            //Initialize the adapter with the recyclerview object
            recyclerView.setAdapter(recyclerAdapter);

        }
        catch (Exception e){
            Toast.makeText(this, "Could not load The Books", Toast.LENGTH_SHORT).show();
            Log.d("What Happened : ", String.valueOf(e));
        }

    }

    public void SignOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}