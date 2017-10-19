package com.example.krishna.myapp.MVP;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

import com.example.krishna.myapp.Activities.FoodActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Krishna on 10/17/2017.
 */

public interface FoodMVP {
    interface FoodView{

    }
    interface FoodPresenter{
        void map(Intent i, GoogleMap googleMap);
    }
}
