package com.hackriddle.textwithoutbarriers.Functionality;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;


/**
 * Created by dmsfabiano on 11/11/17.
 */

public class FirebaseMessaging extends FirebaseMessagingService {

    private static final String TAG = "FirabaseMessage";

    // -----------------------------------------------------------------------------
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }
    // -----------------------------------------------------------------------------

    // Function will show notification to the user, received from Firebase
    private void sendNotification(JSONObject json) {
        return;
    }

}

