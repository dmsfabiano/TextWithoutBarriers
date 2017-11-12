package com.hackriddle.textwithoutbarriers.Chat;

/**
 * Created by david on 11/11/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackriddle.textwithoutbarriers.Functionality.Preference_Manager;
import com.hackriddle.textwithoutbarriers.R;

import java.util.ArrayList;

public class BlankFragment extends Fragment {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    public BlankFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        rv.setHasFixedSize(false);
        final ArrayList<conversationData> dummie_list = new ArrayList<>();
        final adapterConversations adapter = new adapterConversations(dummie_list);
        rv.setAdapter(adapter);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getUid()).child("rooms");
        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for (DataSnapshot da : dataSnapshot.getChildren()) {
                            if(da.hasChild("reciever")) {
                                if(da.child("reciever").getValue(String.class).equals(Preference_Manager.getInstance(getContext()).getEmail()))
                                {
                                    if(!dummie_list.contains(new conversationData(da.child("reciever").getValue(String.class), "", 0))) {
                                        dummie_list.add(new conversationData(da.child("reciever").getValue(String.class), "", 0));
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError){
                        //handle databaseError
                    }
                });
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for(DataSnapshot ds: dataSnapshot.getChildren()) {
                            if (i != 0) {
                                break;
                            }
                            if(!ds.child("last").getValue(String.class).equals(""))
                            {
                                if(!dummie_list.contains(new conversationData(ds.child("email").getValue(String.class), ds.child("last").getValue(String.class), 0)))
                                {
                                    dummie_list.add(new conversationData(ds.child("email").getValue(String.class), ds.child("last").getValue(String.class), 0));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            else if(!dummie_list.contains(new conversationData(ds.child("email").getValue(String.class), "", 0)))
                            {
                                dummie_list.add(new conversationData(ds.child("email").getValue(String.class), "", 0));
                                adapter.notifyDataSetChanged();

                            }
                            i++;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });



        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

}

