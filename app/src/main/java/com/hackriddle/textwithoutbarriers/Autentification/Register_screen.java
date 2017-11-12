package com.hackriddle.textwithoutbarriers.Autentification;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackriddle.textwithoutbarriers.Chat.MainActivity;
import com.hackriddle.textwithoutbarriers.Functionality.Preference_Manager;
import com.hackriddle.textwithoutbarriers.R;

public class Register_screen extends AppCompatActivity implements View.OnClickListener {

    private Button registration_btn;
    private EditText user_email;
    private EditText user_password;
    private EditText user_password_two;
    private String email;
    private String password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // If user is already logged in then we should not be here.
        if (Preference_Manager.getInstance(this).isUserLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        user_email = (EditText) findViewById(R.id.editTextEmail);
        user_password = (EditText) findViewById(R.id.editTextPassword);
        user_password_two = (EditText) findViewById(R.id.editTextReEnteredPassword);
        registration_btn = (Button) findViewById(R.id.buttonRegister);
        mAuth = FirebaseAuth.getInstance();

        registration_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if (v == registration_btn)
        {
            email = user_email.getText().toString().trim();
            password = user_password.getText().toString().trim();

            if (!password.equals(user_password_two.getText().toString().trim())) {
                Toast.makeText(Register_screen.this, "Password fields do not match!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register_screen.this, "Authentification failed!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Preference_Manager.getInstance(Register_screen.this).userLogin(email);
                                FirebaseDatabase db = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = db.getReference("users");
                                myRef.child(mAuth.getUid()).setValue(email);
                                db.getReference("contacts").push().setValue(mAuth.getUid());
                                startActivity(new Intent(Register_screen.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
        }
    }
}
