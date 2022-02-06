package com.example.webstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Locale;

public class Register extends AppCompatActivity implements View.OnClickListener {



    //Init Variables
    private FirebaseAuth mAuth;
    private EditText editFname, editEmail, editPassword;
    private Button registeruser;
    private ProgressBar progressBar;
    private TextView textView;
    private String email,fname,password;
    boolean check;
    private ImageView speakmic;
    private static final int REQUEST_CODE_SPEECH_INPUT = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Register user button init
        registeruser = findViewById(R.id.signup);
        registeruser.setOnClickListener(this);
        //Variables of Reg form
        editFname = findViewById(R.id.editfname);

        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password2);

        textView = findViewById(R.id.contexttext);
        speakmic = findViewById(R.id.mic);
        //For Speech to Text
        speakmic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup:
                registerUser();
                if(check == true) {
                    startActivity(new Intent(this, MainActivity.class));
                    break;
                }
                break;
            case R.id.cancel:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void registerUser() {
        email = editEmail.getText().toString().trim();
        fname =editFname.getText().toString().trim();

        progressBar = findViewById(R.id.progressBar);

        password = editPassword.getText().toString().trim();
        //Check if the inputs are Valid
        if(fname.isEmpty()){
            editFname.setError("Enter First Name !");
            editFname.requestFocus();
            return;
        }


        if(email.isEmpty()){
            editEmail.setError("Enter Email !");
            editEmail.requestFocus();
            return;
        }



        if(password.isEmpty()){
            editPassword.setError("Enter Password !");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        //Authentication Procedure
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this, "User Has been Created", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);

                    check = true;
                }else{
                    Toast.makeText(Register.this, "Error User Creation Failed", Toast.LENGTH_SHORT).show();
                    check = false;
                    progressBar.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);

                }


            }
        });


    }

    private void speak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi !");


        try {
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);

        }catch (Exception e){

        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && null!=intent){
                    ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editFname.setText((CharSequence) result.get(0));
                }
                break;
            }
        }
    }


}
