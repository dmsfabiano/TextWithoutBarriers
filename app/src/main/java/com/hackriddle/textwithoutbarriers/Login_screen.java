package com.hackriddle.textwithoutbarriers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hackriddle.textwithoutbarriers.Functionality.Preference_Manager;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


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