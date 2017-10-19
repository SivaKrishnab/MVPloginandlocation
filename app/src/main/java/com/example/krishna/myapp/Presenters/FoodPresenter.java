package com.example.krishna.myapp.Presenters;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.krishna.myapp.Activities.FoodActivity;
import com.example.krishna.myapp.MVP.FoodMVP;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Krishna on 10/17/2017.
 */

public class FoodPresenter implements FoodMVP.FoodPresenter {
    FoodActivity activity;
    GoogleMap mMap;
    Location location;
Intent intent;
    public FoodPresenter(FoodActivity foodActivity) {
        this.activity = foodActivity;
    }

    @Override
    public void map(Intent i, GoogleMap googleMap) {
        intent=i;
        mMap=googleMap;
        Bundle pa=i.getExtras();
        double   l=pa.getDouble("la");
        double lo=pa.getDouble("lo");
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(l,lo),3.0f));
        mMap.addMarker(new MarkerOptions().position(new LatLng(l,lo)));
    }
}



