package com.apolloapps.theledger.About;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.Common.References;
import com.apolloapps.theledger.DataManager.Models.ReferenceModel;

import java.util.List;


/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutViewHolder> {
    private int mReferencesTotal;
    private References mReferences;
    private AboutFragment.AboutFragmentListener mListener;

    public AboutAdapter( References references, AboutFragment.AboutFragmentListener listener) {
        mReferencesTotal = references.getReferenceCount();
        mReferences = references;
        mListener = listener;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AboutListItemView view = new AboutListItemView(parent.getContext());
        return new AboutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutViewHolder holder, final int position) {
        holder.mAboutListItemView.setContents(mReferences.getReferenceObject(position), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.followLink(mReferences.getReferenceObject(position).getReferenceLink());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mReferencesTotal;
    }
}
