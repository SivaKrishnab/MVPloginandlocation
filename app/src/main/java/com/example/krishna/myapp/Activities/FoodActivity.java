package com.example.krishna.myapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krishna.myapp.MVP.FoodMVP;
import com.example.krishna.myapp.Presenters.FoodPresenter;
import com.example.krishna.myapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FoodActivity extends AppCompatActivity implements OnMapReadyCallback, FoodMVP.FoodView {
    SupportMapFragment mapFragment;
    GoogleMap mmap;
    EditText editText;
    Button button;
    RecyclerView recyclerView;
    FoodPresenter foodPresenter;
    double l,lo;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = (EditText) findViewById(R.id.edittext);
        button = (Button) findViewById(R.id.button);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.INVISIBLE);
        foodPresenter = new FoodPresenter(this);
        i=getIntent();

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        recyclerView.setVisibility(View.VISIBLE);
    }
});
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        foodPresenter.map(i,googleMap);
    }
}


