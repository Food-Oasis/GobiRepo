package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    EditText userEmail, userPass;
    Button loginButton, registerButton;
    private FirebaseAuth mAuth;
    //ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ids are what we name our EditTexts in the xml
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPass);
        //loginProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);


        //uses the firebase authentication object we created earlier
        mAuth = FirebaseAuth.getInstance();

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    //sets up the toolbar options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //goes back to the main activity when selected
            case R.id.case_1_action:
                //Toast.makeText(this, "Case 3...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                return true;

            //goes to the about us page when selected
            case R.id.case_2_action:
                startActivity(new Intent(this, AboutUs.class));

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void loginUser() {

        //trim method eliminates leading and trailing spaces
        String email = userEmail.getText().toString().trim();
        String password = userPass.getText().toString().trim();

        if (email.isEmpty()) {
            userEmail.setError("Email field cannot be left blank");
            userEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Please enter a valid email address.");
            userEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            userPass.setError("Password is required!");
            userPass.requestFocus();
            return;
        }

        //loginProgressBar.setVisibility(View.INVISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    UUID.getInstance().setuuid(user.getUid());
                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), VendorActivity.class));
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Login unsuccessful. Please try again!", Toast.LENGTH_LONG).show();
                }

                // ...
            }
        });

    }


}
