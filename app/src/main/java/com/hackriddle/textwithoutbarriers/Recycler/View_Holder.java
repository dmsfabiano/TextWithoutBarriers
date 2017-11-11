package com.hackriddle.textwithoutbarriers.Recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackriddle.textwithoutbarriers.R;

/**
 * Created by david on 11/11/17.
 */

public class View_Holder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView msg;
        ImageView profilePic;

        View_Holder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
            msg = (TextView) itemView.findViewById(R.id.msg);
            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
        }
}
