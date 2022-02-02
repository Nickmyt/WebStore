package com.example.webstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Locale;

public class Bookstore extends AppCompatActivity {
    private TextView textView;
    private String user;
    private Button buttonbuy;
    private FirebaseAuth mAuth;
    private TextToSpeech mtts;
    private FirebaseFirestore db;
    private boolean check ;
    RecyclerView recyclerView;
    int booknum = 0;
    String text;

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


        check = false;
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
                        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                AddtoCart(position);
                            }

                            @Override
                            public void TTs(int position) {
                                ReadDesc(position);
                            }
                        });



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

    public void ReadDesc(int position){
        mtts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                    if (status!= TextToSpeech.ERROR){
                        mtts.setLanguage(Locale.ENGLISH);

                    }
                    if(check == false){
                        mtts.speak(desc.get(position),1,null,null);
                        check = true;
                    }else{
                        mtts.stop();
                        mtts.speak(desc.get(position),1,null,null);
                        check = false;
                    }
            }
        });



    }
    public void AddtoCart(int position){
        if(avail.get(position) != 0){
            booknum+=1;
            


        }else{
            Toast.makeText(Bookstore.this, "Sorry Book not available", Toast.LENGTH_SHORT);
        }
    }

}