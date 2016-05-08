package com.apolloapps.theledger.Features.Accounts;

import android.support.v7.widget.RecyclerView;

/**
 * Created by AMoreira on 4/25/16.
 */
public class AccountsViewHolder extends RecyclerView.ViewHolder {

    AccountsListViewItem mAccountsListViewItem;

    public AccountsViewHolder(AccountsListViewItem itemView) {
        super(itemView);
        mAccountsListViewItem = itemView;
    }
}
