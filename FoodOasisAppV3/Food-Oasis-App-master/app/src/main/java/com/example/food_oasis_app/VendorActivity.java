package com.example.food_oasis_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.food_oasis_app.view_items_adapter.itemListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
public class VendorActivity extends AppCompatActivity {

    private TextView item1View, item2View, item3View, item4View, item5View;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // private Firebase item1Ref, item2Ref, item3Ref, item4Ref, item5Ref;
    private ListView mListView;
    private String userID;
    ArrayList<VendorInformation> itemList = new ArrayList<>();
    Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        signOutButton = findViewById(R.id.signOutButton2);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendorSignOut();
            }
        });


        getKey();


    }

    public void addItemsbuttonClicked(View v) {
        startActivity(new Intent(getApplicationContext(), AddItems.class));
    }


    public void getKey() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference item1Ref = database.getReference("vendors");
        String uuid = UUID.getInstance().getuuid();
        item1Ref.orderByChild("uuid").equalTo(uuid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot val : dataSnapshot.getChildren()) {
                    String customerKey = val.getKey();
                    UUID.getInstance().setVendorKey(customerKey);
                }
                populateItems();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                // Failed to read value
                Log.w("error", "Failed to read value.", databaseError.toException());
            }
        });

    }


    public void populateItems() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference item1Ref = database.getReference("vendors/" + UUID.getInstance().getVendorKey() + "/items");

        item1Ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot val : dataSnapshot.getChildren()) {
                    VendorInformation dbItem = val.getValue(VendorInformation.class);
                    VendorInformation addItem = new VendorInformation(dbItem.getItem(),dbItem.getPrice(),dbItem.getQuantity());
                    addItem.setItemKey(val.getKey());
                    itemList.add(addItem);
                }

                setAdapterView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                // Failed to read value
                Log.w("error", "Failed to read value.", databaseError.toException());
            }
        });


    }


    public void setAdapterView() {
        final ListView mListView = (ListView) findViewById(R.id.listView);
        itemListAdapter adapter = new itemListAdapter(this, R.layout.adapter_view_layout, itemList);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                VendorInformation o = (VendorInformation) mListView.getItemAtPosition(position);
                activateIntent(o);
            }
        });
    }


    public void activateIntent(VendorInformation o) {

        Intent intent = new Intent(this, EditItems.class);

        intent.putExtra("edititem", o.getItem());
        intent.putExtra("editquantity", o.getQuantity());
        intent.putExtra("editprice", o.getPrice());
        intent.putExtra("itemKey", o.getItemKey());

        startActivity(intent);
    }
    public void vendorSignOut() {
        UUID.getInstance().setVendorKey(null);
        mAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

}

