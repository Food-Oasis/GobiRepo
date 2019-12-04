package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VendorLogin extends AppCompatActivity {

    EditText userEmail, userPass;
    private FirebaseAuth mAuth;
    //ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        //ids for buttons
        Button loginButton = (Button) findViewById(R.id.vendorLoginBtn);
        Button registerButton = (Button) findViewById(R.id.vendorLoginRegBtn);

        //ids are what we name our EditTexts in the xml
        userEmail = findViewById(R.id.login_vendor_email);
        userPass = findViewById(R.id.vendor_password);

        //uses the firebase authentication object we created earlier
        mAuth = FirebaseAuth.getInstance();

        //logs the user in
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        //takes the user to the register screen
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VendorRegistration1.class));
            }
        });



    }


    private void loginUser(){
        //trim method eliminates leading and trailing spaces
        String email = userEmail.getText().toString().trim();
        String password = userPass.getText().toString().trim();

        if(email.isEmpty()){
            userEmail.setError("Email field cannot be left blank");
            userEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("Please enter a valid email address.");
            userEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            userPass.setError("Password is required.");
            userPass.requestFocus();
            return;
        }

        //loginProgressBar.setVisibility(View.INVISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //sign in success, updated UI with the sign-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    UUID.getInstance().setuuid(user.getUid());
                    Toast.makeText(getApplicationContext(),"Login successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), VendorActivity.class));
                }
                else{
                    //if the sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(),"Login unsuccessful. Please try again!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
