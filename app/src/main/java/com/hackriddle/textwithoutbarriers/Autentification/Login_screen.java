package com.hackriddle.textwithoutbarriers.Autentification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import com.hackriddle.textwithoutbarriers.Functionality.Preference_Manager;
import com.hackriddle.textwithoutbarriers.R;


public class Login_screen extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // If the user is already logged in, we do not want to be here, we go to main layout
        if (Preference_Manager.getInstance(this).isUserLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), Login_screen.class));
            finish();
            return;
        }
    }
}