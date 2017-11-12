package com.hackriddle.textwithoutbarriers.Chat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackriddle.textwithoutbarriers.Functionality.Preference_Manager;
import com.hackriddle.textwithoutbarriers.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.R.attr.value;

public class Chat_room extends AppCompatActivity {

    // This class is a very basic approach, can probably find something better out there.

    private Button button_send;
    private EditText input_message;
    private TextView chat_conversation;
    private String user_we_are_talking;
    private String temp_key;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private String id;
    private String dsKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        button_send = (Button) findViewById(R.id.btn_send);
        input_message = (EditText) findViewById(R.id.msg_input);
        chat_conversation = (TextView) findViewById(R.id.textView);
        user_we_are_talking = getIntent().getExtras().get("email").toString();
        mAuth = FirebaseAuth.getInstance();

        setTitle("Talking to " + user_we_are_talking);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = FirebaseDatabase.getInstance().getReference().push().getKey();

                String email = Preference_Manager.getInstance(Chat_room.this).getEmail();

                FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Boolean roomExist = false;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    String r = ds.child("reciever").getValue(String.class);
                                    if (dataSnapshot.hasChild("reciever")) {
                                        if (r.equals(user_we_are_talking)) {
                                            dsKey = ds.getKey();
                                            Log.d("DSKEY1", ds.getKey());
                                            ref = FirebaseDatabase.getInstance().getReference().child("users").child(ds.getKey()).child("rooms").child(id);
                                            ref.child("email_talking_to").setValue(user_we_are_talking);
                                            ref.child("MSG").setValue(input_message.getText().toString());
                                            roomExist = true;
                                            break;
                                        }
                                    }
                                }
                                if (!roomExist) {
                                    dsKey = mAuth.getUid();
                                    Log.d("DSKEY2", dsKey);
                                    ref = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid()).child("rooms").child(id);
                                    ref.child("email_talking_to").setValue(user_we_are_talking);
                                    ref.child("MSG").setValue(input_message.getText().toString());
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                //handle databaseError
                            }
                        });
            }
        });

        if (!(dsKey == null)) {
            FirebaseDatabase.getInstance().getReference().child("users").child(dsKey).child("rooms").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.d("Executed", "DataSnapshot");
                    append_chat_conversation(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    append_chat_conversation(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else {
            FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid()).child("rooms").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.d("Executed", "DataSnapshot");
                    append_chat_conversation(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    append_chat_conversation(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    private String chat_msg, chat_user_name;

    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        int j = 0;
        Iterator i = dataSnapshot.getChildren().iterator();

        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            if (j == 0) {
                j++;
                continue;
            }
            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            i.next();
            chat_conversation.append(Preference_Manager.getInstance(this).getEmail() + " : " + chat_msg + " \n");
            j++;
        }
    }
}
