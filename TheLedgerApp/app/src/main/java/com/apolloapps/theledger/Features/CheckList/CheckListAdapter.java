package com.apolloapps.theledger.Features.CheckList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.apolloapps.theledger.DataManager.Models.CheckListModel;

import java.util.List;

/**
 * Created by AMoreira on 8/23/16.
 */
public class CheckListAdapter extends RecyclerView.Adapter<CheckListItemViewHolder> {

    private Context mContext;
    private List<CheckListModel> mChecklistList;
    private CheckListFragment.CheckListFragmentListener mListener;


    public CheckListAdapter(List<CheckListModel>list, Context context, CheckListFragment.CheckListFragmentListener listener){
        mChecklistList = list;
        mContext = context;
        mListener = listener;
    }


    @Override
    public CheckListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckListItem viewItem = new CheckListItem(parent.getContext());
        return new CheckListItemViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(CheckListItemViewHolder holder, final int position) {
       holder.checkListItem.setContents(mChecklistList.get(position), new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  mListener.checkItem(isChecked,mChecklistList.get(position).getCheckListId());
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
        return mChecklistList.size();
    }

}
