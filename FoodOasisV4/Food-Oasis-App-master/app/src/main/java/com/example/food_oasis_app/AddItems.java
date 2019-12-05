package com.example.food_oasis_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Button;

public class AddItems extends AppCompatActivity {
    EditText item, price, quantity;
    private String userID;

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        addButton = findViewById(R.id.editButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

    }

    public void addItem() {
        String vendorKey = UUID.getInstance().getVendorKey();
        item = findViewById(R.id.setItemName);
        price = findViewById(R.id.setItemPrice);
        quantity = findViewById(R.id.setItemQuantity);
        String itemVal = item.getText().toString();
        String priceVal = price.getText().toString();
        String quantityVal = quantity.getText().toString();
        VendorInformation newItem = new VendorInformation(itemVal, priceVal, quantityVal);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference item1Ref = database.getReference("vendors/"+vendorKey+ "/" + "items");

        item1Ref.push().setValue(newItem);

        //after pushed show vendor activity class again
        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), VendorActivity.class));


    }

}
