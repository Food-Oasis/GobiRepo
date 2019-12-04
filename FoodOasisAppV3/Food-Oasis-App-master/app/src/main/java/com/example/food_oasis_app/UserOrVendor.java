package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserOrVendor extends AppCompatActivity {

    Button userButton, vendorButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_vendor);

        userButton = (Button) findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), googleMapActivity.class));
            }
        });

        vendorButton = (Button) findViewById(R.id.vendorButton);
        vendorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    //creates dropdown menu and options in the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //pulls up the main activity when selected
            case R.id.case_1_action:
                //Toast.makeText(this, "Case 3...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserOrVendor.this, MainActivity.class));
                return true;

            //pulls up the about us page when selected
            case R.id.case_2_action:
                startActivity(new Intent(UserOrVendor.this, AboutUs.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
}
