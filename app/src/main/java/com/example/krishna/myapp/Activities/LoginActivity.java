package com.example.krishna.myapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.krishna.myapp.MVP.LoginMVP;
import com.example.krishna.myapp.Presenters.LoginPresenter;
import com.example.krishna.myapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity implements LoginMVP.view {
    SignInButton signInButton;
    private GoogleApiClient mGoogleApiClient;
    LocationManager locationManager;
    LocationListener locationListener;
    double l, lo;
    LoginPresenter presenterlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInButton = (SignInButton) findViewById(R.id.google);
        presenterlogin = new LoginPresenter(this);
        presenterlogin.createGoogleClient(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                l = location.getLatitude();
                lo = location.getLongitude();
                locationManager.removeUpdates(locationListener);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterlogin.signinbutton(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenterlogin.onactivityResult(LoginActivity.this, requestCode, resultCode, data);
    }
    @Override
    public void updateUi() {

        Intent in=new Intent(LoginActivity.this,FoodActivity.class);
        Bundle bundle=new Bundle();
        bundle.putDouble("la",l);
        bundle.putDouble("lo",lo);
        in.putExtras(bundle);
        startActivity(in);
        finish();
    }
    @Override
    public void shoemessage(GoogleSignInResult result) {
        Toast.makeText(LoginActivity.this,result.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sigin(GoogleSignInOptions gso) {
        signInButton.setScopes(gso.getScopeArray());
    }
}
