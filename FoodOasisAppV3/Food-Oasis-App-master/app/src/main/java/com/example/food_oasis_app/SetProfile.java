package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class SetProfile extends AppCompatActivity {

    //private Firebase customerRef;
    //Firebase database;
    // DatabaseReference myRef;
    Button loginButton;
    Button saveButton;
    EditText setBusinessName;
    EditText setFirstName;
    EditText setLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCustomer();
                Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        /*loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });*/

    }

    private void setCustomer() {
        Log.d("***", "Running");

        String firstName = "";
        String lastName = "";
        String businessName = "";
        Log.d("***","Running 0");

        setBusinessName = findViewById(R.id.setBusinessName);
        setFirstName = findViewById(R.id.setFirstName);
        setLastName = findViewById(R.id.setLastName);

        Log.d("***","Running 1");


        firstName = setFirstName.getText().toString();
        lastName = setLastName.getText().toString();
        businessName = setBusinessName.getText().toString();
        String uuid =  UUID.getInstance().getuuid();
        SetCustomer customer1 = new SetCustomer(firstName, lastName, businessName, uuid);
        //isplayName = setBusinessName.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("vendors");

        //database = FirebaseDatabase.getInstance();
        //myRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://food-oasis-app.firebaseio.com/vendors");
        myRef.push().setValue(customer1);

        //myRef.setValue("Hello, World!");

        //databaseReference=firebaseDatabase.getReferenceFromUrl("https://food-oasis-app.firebaseio.com/vendors");
        Log.d("***","Running A");
        //customerRef = new Firebase("https://food-oasis-app.firebaseio.com/vendors"); //something wrong here, not link
        Log.d("***","Running B");
        //customerRef.push().setValue(new SetCustomer(firstName, lastName, displayName)); //something wrong here maybe
    }

}
