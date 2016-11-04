package com.apolloapps.theledger.Features.Accounts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.DataManager.Models.AccountModel;

import java.util.List;

/**
 * Created by AMoreira on 4/25/16.
 */
public class AccountsAdapter extends RecyclerView.Adapter<AccountsViewHolder> {

    private List<AccountModel> mAccountList;
    private Context mContext;
    private AccountsListFragment.AccountsListFragmentListener mListener;

    public AccountsAdapter(Context context, List<AccountModel> accountList, AccountsListFragment.AccountsListFragmentListener listener) {
        mAccountList = accountList;
        mContext = context;
        mListener = listener;
    }


    @Override
    public AccountsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AccountsListViewItem view = new AccountsListViewItem(parent.getContext());
        return new AccountsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountsViewHolder holder, final int position) {
        holder.mAccountsListViewItem.setContents(mAccountList.get(position), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View
                mListener.viewAccountDetails(mAccountList.get(position).getAccountId());
            }
        }, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.peakAccount(mAccountList.get(position));
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAccountList.size();

    }
}
