package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button vendorBtn = (Button) findViewById(R.id.vendorButton);

        //takes the user to the login screen
        vendorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VendorLogin.class));
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app_toolbar, menu);
        return true;
    }


    //creates dropdown menu and options in the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //pulls up the Google map activity
            case R.id.view_map_action:
                Toast.makeText(this, "Google Map API selected...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, googleMapActivity.class));
                return true;

            //pulls up the main activity when selected
            case R.id.case_2_action:
                //Toast.makeText(this, "Case 3...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;

            //pulls up the about us page when selected
            case R.id.case_3_action:
                startActivity(new Intent(MainActivity.this, AboutUs.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
}
