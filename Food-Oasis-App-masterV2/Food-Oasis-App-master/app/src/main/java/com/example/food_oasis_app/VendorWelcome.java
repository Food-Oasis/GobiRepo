package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class VendorWelcome extends AppCompatActivity {

    Button setButton, viewButton, signOutButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_welcome);



        //takes the user to go set their profile
        setButton = (Button) findViewById(R.id.setBtn);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SetProfile.class));
            }
        });


        //takes the user to the items list
        viewButton = (Button) findViewById(R.id.btn_add_edit_items);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VendorActivity.class));
            }
        });


        //activates the log out function
        signOutButton = (Button) findViewById(R.id.btn_Logout);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendorSignOut();
            }
        });

    }


    //function for signing the user out
    public void vendorSignOut() {
        UUID.getInstance().setVendorKey(null);
        mAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), VendorLogin.class));
    }
}
