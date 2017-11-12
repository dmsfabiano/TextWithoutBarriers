package com.hackriddle.textwithoutbarriers.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackriddle.textwithoutbarriers.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by david on 11/11/17.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<View_Holder> {
    List<RecyclerData> list = Collections.emptyList();
    Context context;

    public RecycleViewAdapter(List<RecyclerData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.msg.setText(list.get(position).msg);
        holder.profilePic.setImageResource(list.get(position).imageId);

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, RecyclerData RecyclerData) {
        list.add(position, RecyclerData);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified RecyclerData object
    public void remove(RecyclerData RecyclerData) {
        int position = list.indexOf(RecyclerData);
        list.remove(position);
        notifyItemRemoved(position);
    }
}
