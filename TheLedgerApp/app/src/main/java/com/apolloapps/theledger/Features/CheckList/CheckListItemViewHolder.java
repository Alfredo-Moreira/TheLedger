package com.apolloapps.theledger.Features.CheckList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by AMoreira on 8/23/16.
 */
public class CheckListItemViewHolder extends RecyclerView.ViewHolder {

    CheckListItem checkListItem;

    public CheckListItemViewHolder(CheckListItem itemView) {
       super(itemView);
        checkListItem = itemView;
    }
}
