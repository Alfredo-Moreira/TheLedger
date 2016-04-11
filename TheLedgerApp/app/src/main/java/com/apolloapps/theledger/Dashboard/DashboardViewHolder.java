package com.apolloapps.theledger.Dashboard;

import android.support.v7.widget.RecyclerView;

/**
 * Created by AMoreira on 4/8/16.
 */
public class DashboardViewHolder extends RecyclerView.ViewHolder {
    DashboardListItemView mDashboardListItemView;

    public DashboardViewHolder(DashboardListItemView itemView) {
        super(itemView);
        mDashboardListItemView = itemView;
    }
}
