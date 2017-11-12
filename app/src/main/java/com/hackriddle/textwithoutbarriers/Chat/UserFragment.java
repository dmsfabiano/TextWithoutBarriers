package com.hackriddle.textwithoutbarriers.Chat;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackriddle.textwithoutbarriers.R;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.attr.value;
import static android.R.style.Widget;

/**
 * Created by david on 11/11/17.
 */

public class UserFragment extends Fragment {

    int tab_id;

    public UserFragment() {
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
        rv.setHasFixedSize(false);
        final ArrayList<widgetData> dummie_list = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final MyAdapter adapter = new MyAdapter(dummie_list);
        rv.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("contacts").child(mAuth.getUid());
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren())
                        {
                            Log.d("1234567890qwertyuiop", ds.getValue(String.class));
                            if(!dummie_list.contains(new widgetData(ds.getValue(String.class), "FUCK FIREBASE", 0)))
                            {
                                Log.d("1234567890qwertyuiop", dummie_list.toString());
                                dummie_list.add(new widgetData(ds.getValue(String.class), "FUCK FIREBASE", 0));
                                adapter.notifyDataSetChanged();

                            }
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