package com.example.krishna.myapp.Presenters;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.example.krishna.myapp.Activities.LoginActivity;
import com.example.krishna.myapp.MVP.LoginMVP;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Krishna on 10/17/2017.
 */

public class LoginPresenter extends FragmentActivity implements LoginMVP.Presenter, GoogleApiClient.OnConnectionFailedListener {
    LoginActivity login;
    GoogleApiClient m;
    int RC = 2016;

    double latitude;

    public LoginPresenter(LoginActivity loginActivity) {
        this.login = loginActivity;
    }

    @Override
    public void signinbutton(LoginActivity loginActivity) {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(m);
        loginActivity.startActivityForResult(signInIntent, RC);

    }

    @Override
    public void onactivityResult(LoginActivity loginActivity, int requestCode, int resultCode, Intent data) {
        if (requestCode == RC) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                login.updateUi();
            }else{
                login.shoemessage(result);
            }
        }
    }

    @Override
    public void createGoogleClient(LoginActivity loginActivity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        login.sigin(gso);
        m = new GoogleApiClient.Builder(loginActivity)
                .enableAutoManage(loginActivity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
