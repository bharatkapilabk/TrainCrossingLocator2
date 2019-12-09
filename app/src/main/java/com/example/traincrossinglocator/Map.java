package com.example.traincrossinglocator;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class Map extends AppCompatActivity {
    FusedLocationProviderClient myclient;
    Location lastLocation;
    double dlatitude, dlongitude;
    LatLng latlng1;
    SupportMapFragment supportMapFragment;
    GoogleMap gmap;
    boolean flag = false;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        supportMapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        myclient = LocationServices.getFusedLocationProviderClient(this);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            latlng1= (LatLng) bundle.get("latlong");
        }
        checkLocationPermission();
    }
    private void checkLocationPermission()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            flag = true;
            Toast.makeText(this,"Location Permission Granted",Toast.LENGTH_SHORT).show();

            myclient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        lastLocation = location;
                        dlatitude  = lastLocation.getLatitude();
                        dlongitude = lastLocation.getLongitude();
                        initMap();
                    }
                }
            });
        }
    }
    void initMap()
    {
        Toast.makeText(this, "Initialize Map", Toast.LENGTH_SHORT).show();

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap)
            {
                gmap = googleMap;
                if(flag)
                {

                    //LatLng latlng1=new LatLng(31.342205,75.576007);
                    /*LatLng latlng2=new LatLng(31.3380797,75.5816489);
                    LatLng latlng3=new LatLng(31.3393936,75.5797917);*/
                    LatLng mylatlng = new LatLng(dlatitude,dlongitude);
                    gmap.moveCamera(CameraUpdateFactory.newLatLng(mylatlng));
                    gmap.setMyLocationEnabled(true);;

                    MarkerOptions markerOptions = new MarkerOptions();
                    MarkerOptions markerOptions1 = new MarkerOptions();
                    /*MarkerOptions markerOptions2 = new MarkerOptions();
                    MarkerOptions markerOptions3 = new MarkerOptions();*/
                    markerOptions.position(mylatlng);
                    markerOptions.title("You are here");
                    gmap.addMarker(markerOptions);
                    markerOptions1.position(latlng1);
                    gmap.addMarker(markerOptions1);
                    /*markerOptions2.position(latlng2);
                    gmap.addMarker(markerOptions2);
                    markerOptions3.position(latlng3);
                    gmap.addMarker(markerOptions3);*/
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==1)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                checkLocationPermission();
            }
            else
            {
                Toast.makeText(this,"Location Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }}
}