package com.example.krishna.myapp.MVP;

import android.content.Intent;

import com.example.krishna.myapp.Activities.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by Krishna on 10/17/2017.
 */

public interface LoginMVP {
    interface view{
        void updateUi();

        void shoemessage(GoogleSignInResult result);

        void sigin(GoogleSignInOptions gso);
    }
    interface Presenter{


        void signinbutton(LoginActivity loginActivity);

        void onactivityResult(LoginActivity loginActivity, int requestCode, int resultCode, Intent data);

        void createGoogleClient(LoginActivity loginActivity);

    }
}
