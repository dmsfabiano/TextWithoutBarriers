package com.hackriddle.textwithoutbarriers.Chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackriddle.textwithoutbarriers.R;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by david on 11/12/17.
 */

public class adapterConversations extends RecyclerView.Adapter<adapterConversations.MyViewHolder> {
    private ArrayList<conversationData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView m2TextView;

        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            m2TextView = (TextView) v.findViewById(R.id.tv_blah);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public adapterConversations(ArrayList<conversationData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public adapterConversations.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position).getUser_name());
        holder.m2TextView.setText(mDataset.get(position).getLast_message());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mDataset.get(position).getUser_name();
                Log.d("CardView", "CardView Clicked: " + username);
                Intent intent = new Intent(holder.itemView.getContext(),Chat_room.class);
                intent.putExtra("email", username);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

