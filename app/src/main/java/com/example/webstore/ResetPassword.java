package com.example.webstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    private Button resetButton, backButton;
    private EditText editTextEmail;
    private ProgressBar progressBar;

    FirebaseAuth MAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetButton = findViewById(R.id.resetButton);
        editTextEmail = findViewById(R.id.editTextEmailAddress2);
        progressBar = findViewById(R.id.progressBar2);
        backButton = findViewById(R.id.backbutton);
        MAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMeth();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public void resetMeth(){
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Please enter your Email Address");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a Valid Email Address");
            editTextEmail.requestFocus();
            return;
        }
    progressBar.setVisibility(View.VISIBLE);
        MAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPassword.this, "Reset email sent", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(ResetPassword.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ResetPassword.this, "Something went Wrong Try Again", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
            }
        }
        );

    }



}