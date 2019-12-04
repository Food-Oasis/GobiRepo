package com.foodtracker.foodoasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VendorLogin extends AppCompatActivity {

    EditText userEmail, userPass;
    Button loginBtn, registerBtn;
    private FirebaseAuth mAuth;
    //ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        Button registerBtn = (Button) findViewById(R.id.btn_register);
        Button loginButton = (Button) findViewById(R.id.loginBtn);

        //logs the user in
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        //takes the user to the register screen
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VendorLogin.this, VendorRegistration.class));
            }
        });



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
