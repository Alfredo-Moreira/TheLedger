package com.apolloapps.theledger.Features.Bills;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by AMoreira on 6/21/16.
 */
public class BillsViewHolder extends RecyclerView.ViewHolder {

    BillsListViewItem mBillsListViewItem;

    public BillsViewHolder(BillsListViewItem itemView) {
        super(itemView);
        mBillsListViewItem = itemView;
    }
}
