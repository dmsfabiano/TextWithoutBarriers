package com.hackriddle.textwithoutbarriers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hackriddle.textwithoutbarriers.Functionality.Preference_Manager;

public class Register_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // If user is already logged in then we should not be here.
        if (Preference_Manager.getInstance(this).isUserLoggedIn()) {
            startActivity(new Intent(this, Login_screen.class));
            finish();
            return;
        }
    }
}
