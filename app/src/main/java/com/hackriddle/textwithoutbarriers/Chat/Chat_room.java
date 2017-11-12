package com.hackriddle.textwithoutbarriers.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.hackriddle.textwithoutbarriers.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chat_room extends AppCompatActivity {

    // This class is a very basic approach, can probably find something better out there.

    private Button button_send;
    private EditText input_message;
    private TextView chat_conversation;
    private String user_we_are_talking;
    private String temp_key;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        button_send = (Button) findViewById(R.id.btn_send);
        input_message = (EditText) findViewById(R.id.msg_input);
        chat_conversation = (TextView) findViewById(R.id.textView);
        user_we_are_talking = getIntent().getExtras().get("email").toString();

        setTitle("Talking to " + user_we_are_talking);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ALL THIS LOGIC NEEDS TO BE REVIEWED/Untested, for more help go: https://www.youtube.com/watch?v=uX6_w6yhj4E
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid()).child("rooms").child(user_we_are_talking);

                Map<String, Object> map = new HashMap<>();
                temp_key = ref.push().getKey();
                ref.updateChildren(map);

                DatabaseReference message_root = ref.child(temp_key);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("email", user_we_are_talking);
                map2.put("msg", input_message.getText().toString());

                message_root.updateChildren(map2);
            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
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

    private String chat_msg, chat_user_name;

    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {

            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot) i.next()).getValue();

            chat_conversation.append(chat_user_name + " : " + chat_msg + " \n");
        }
    }
}
