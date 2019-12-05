package com.example.food_oasis_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class googleMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mMapView;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Check gps is enable or not
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //function to enable gps
            OnGPS();
        } else {
            //GPS IS ALREADY ON
            getLocation();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //sets up the toolbar options
    /*@Override
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
    }*/

    // function to populate map with current locations and other locations.
    //  still needs code for populating the data stored in the database[
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker
        LatLng curLocation = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
//        double a, b;
//        a = Double.parseDouble(latitude);
//        b = Double.parseDouble(longitude);
//        LatLng currentLocation = new LatLng(a, b);
        mMap.addMarker(new MarkerOptions().position(curLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocation,10));

        LatLng conchHeaven = new LatLng(33.782116, -84.384645);
        mMap.addMarker(new MarkerOptions().position(conchHeaven).title("Conch Heaven"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(conchHeaven));

        LatLng sevanandaNaturalFoodsMarket = new LatLng(33.758862, -84.349136);
        mMap.addMarker(new MarkerOptions().position(sevanandaNaturalFoodsMarket).title("Sevananda Natural Foods Market"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sevanandaNaturalFoodsMarket));

        LatLng healthUnlimited = new LatLng(33.815239, -84.309433);
        mMap.addMarker(new MarkerOptions().position(healthUnlimited).title("Health Unlimited"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(healthUnlimited));
    }

    //function for getting device  current location checking for permission
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //function for getting the user location
    private void getLocation() {
        //Check permissions
        if (ActivityCompat.checkSelfPermission(googleMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(googleMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps != null) {
                double lat = LocationGps.getLatitude();
                double longi = LocationGps.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                Log.d("***", "Your Current Location is: \n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else if (LocationNetwork != null) {
                double lat = LocationNetwork.getLatitude();
                double longi = LocationNetwork.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                Log.d("***", "Your Current Location is: \n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else if (LocationPassive != null) {
                double lat = LocationPassive.getLatitude();
                double longi = LocationPassive.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                Log.d("***", "Your Current Location is: \n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Cant Get Your Location", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

