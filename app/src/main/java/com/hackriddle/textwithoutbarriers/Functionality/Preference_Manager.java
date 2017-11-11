package com.hackriddle.textwithoutbarriers.Functionality;


import android.content.Context;
import android.content.SharedPreferences;


public class Preference_Manager {

    private static Preference_Manager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpreferences";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_TOKEN = "usertoken";

    // -----------------------------------------------------------------------------

    private Preference_Manager(Context context) {
        mCtx = context;
    }

    public static synchronized Preference_Manager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preference_Manager(context);
        }
        return mInstance;
    }

    // -----------------------------------------------------------------------------

    public boolean userLogin(String email){ // Saves the information temporarily into final strings.

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
        return true;
    }

    public boolean isUserLoggedIn(){ // If we have some information then the user is currently logged in.
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_EMAIL, null) != null){
            return true;
        }
        return false;
    }
    // -----------------------------------------------------------------------------

    public String getEmail(){ // Function returns the email of the currently logged in user.
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public boolean saveEmail(String email){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
        return true;
    }


    // -----------------------------------------------------------------------------

    public boolean saveToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
        return true;
    }

    public String getToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    // -----------------------------------------------------------------------------


}
