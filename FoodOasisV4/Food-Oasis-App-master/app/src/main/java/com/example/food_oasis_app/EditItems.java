package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EditItems extends AppCompatActivity {
    private String item;
    private String quantity;
    private String price;
    private String itemKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        item = getIntent().getStringExtra("edititem");
       quantity= getIntent().getStringExtra("editquantity");
       price= getIntent().getStringExtra("editprice");
       itemKey = getIntent().getStringExtra("itemKey");

        TextView item_text = findViewById(R.id.setItemName);
        TextView quantity_text = findViewById(R.id.setItemQuantity);
        TextView price_text = findViewById(R.id.setItemPrice);

        item_text.setText(item);
        quantity_text.setText(quantity);
        price_text.setText(price);

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItem();
            }
        });

    }


    public void editItem() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference item1Ref = database.getReference("vendors/" + UUID.getInstance().getVendorKey() + "/items/" + itemKey);

        TextView item_text = findViewById(R.id.setItemName);
        TextView quantity_text = findViewById(R.id.setItemQuantity);
        TextView price_text = findViewById(R.id.setItemPrice);

        item = item_text.getText().toString();
        quantity = quantity_text.getText().toString();
        price = price_text.getText().toString();

        VendorInformation itemUpdate = new VendorInformation(item,quantity,price);


        item1Ref.setValue(itemUpdate)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        activatePreviousIntent();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(getApplicationContext(), "Update unsuccessful. Please try again!", Toast.LENGTH_LONG).show();
                    }
                });


    }


    public void activatePreviousIntent() {
        Intent intent = new Intent(this, VendorActivity.class);
        startActivity(intent);
    }
}
