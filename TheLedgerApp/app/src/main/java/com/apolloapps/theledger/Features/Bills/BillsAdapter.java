package com.apolloapps.theledger.Features.Bills;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.DataManager.Models.BillModel;

import java.util.List;

/**
 * Created by AMoreira on 6/21/16.
 */
public class BillsAdapter extends RecyclerView.Adapter<BillsViewHolder> {

    private List<BillModel> mBillList;
    private Context mContext;
    private BillsListFragment.BillsListFragmentListener mListener;

    public BillsAdapter(List<BillModel> list, Context context, BillsListFragment.BillsListFragmentListener listener) {
        mBillList = list;
        mContext = context;
        mListener = listener;
    }


    @Override
    public BillsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       BillsListViewItem viewItem = new BillsListViewItem(parent.getContext());
        return new BillsViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(BillsViewHolder holder,final int position) {
       holder.mBillsListViewItem.setContents(mBillList.get(position), new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             mListener.viewBillDetails(mBillList.get(position).getBillId());
           }
       }, new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               return false;
           }
       });
    }

    @Override
    public int getItemCount() {
        return mBillList.size();
    }
}
