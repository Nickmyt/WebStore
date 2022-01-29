package com.example.webstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variable Declaration
    private Button register , PassReset;
    private String email,password;
    private FirebaseAuth mAuth;
    private EditText TextEmail , TextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        PassReset = findViewById(R.id.PassReset);
        PassReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResetPassword.class);
                startActivity(intent);
            }
        });

        TextEmail = findViewById(R.id.editTextEmailAddress);
        TextPassword = findViewById(R.id.editTextTextPassword);
        if(TextEmail.getText()!=null){
            TextPassword.getText().clear();
            TextEmail.getText().clear();
        }

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.Signin:
                Userlogin();
                break;
            case R.id.PassReset:

                break;

        }
    }
    public void Userlogin(){
        //Get string of values
        email = TextEmail.getText().toString().trim();
        password = TextPassword.getText().toString().trim();

        //Check if email and password fields are empty
        if(email.isEmpty()){
            TextEmail.setError("Enter Email !");
            TextEmail.requestFocus();

            return;
        }
        if(password.isEmpty()){
            TextPassword.setError("Please Enter Your Password");
            TextPassword.requestFocus();

            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this,Bookstore.class);
                            intent.putExtra("email",user.getEmail());

                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Login Failed, Check your Credentials", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


}