package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;

public class VendorRegistration extends AppCompatActivity {
    EditText newEmail, newPass, confirmPass;
    private FirebaseAuth mAuth;
    ProgressBar signUpProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newEmail = findViewById(R.id.editTxtEmail);
        newPass = findViewById(R.id.editTxtPwd);
        confirmPass = findViewById(R.id.editTxtConfirmPwd);

        signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    public void registerButton(View v){
        registerUser();
    }

    public void checkAccount(View v){
        startActivity(new Intent(getApplicationContext(), VendorLogin.class));
    }

    private void registerUser(){
        String email = newEmail.getText().toString().trim();
        String password = newPass.getText().toString().trim();
        String confirmPassword = confirmPass.getText().toString().trim();

        if(email.isEmpty()){
            newEmail.setError("Email field cannot be left blank");
            newEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            newEmail.setError("Please enter a valid email address.");
            newEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            newPass.setError("Password field cannot be left blank.");
            newPass.requestFocus();
            return;
        }

        if(password.length() < 6){
            newPass.setError("Minimum password length is 6 characters.");
        }

        if(!confirmPassword.equals(password)){
            confirmPass.setError("Passwords do not match.");
            confirmPass.requestFocus();
            return;
        }

        signUpProgressBar.setVisibility(View.INVISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User registration succesful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), SetProfile.class));
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is already registered.",Toast.LENGTH_LONG).show();
                        signUpProgressBar.setVisibility(View.INVISIBLE);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error occurred. Please try again!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), VendorWelcome.class));
                    }
                }
            }
        });

    }
}
