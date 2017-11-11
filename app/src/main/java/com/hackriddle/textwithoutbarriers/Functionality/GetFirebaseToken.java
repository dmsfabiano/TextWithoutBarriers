package com.hackriddle.textwithoutbarriers.Functionality;


import android.app.ProgressDialog;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by Diego Fabiano on 5/7/2017.
 */

// Updated and reviewed 8/17/2017
public class GetFirebaseToken extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirabaseToken";
    private ProgressDialog progressDialog;

    @Override
    public void onTokenRefresh() {
        //Gets token from fire base.
        String updatedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed token: " + updatedToken);

        // Calling function so save the updated token.
        storeToken(updatedToken);
    }

    // -----------------------------------------------------------------------------

    private void storeToken(String token) {
        //This method will save the token on our Preference manager
        Preference_Manager.getInstance(getApplicationContext()).saveToken(token);
    }

}