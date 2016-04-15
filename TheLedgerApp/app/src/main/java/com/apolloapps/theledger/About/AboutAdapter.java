package com.apolloapps.theledger.About;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.apolloapps.theledger.Common.ReferencesList;


/**
 * Created by AMoreira on 4/13/16.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutViewHolder> {
    private int mReferencesTotal;
    private ReferencesList mReferencesList;
    private AboutFragment.AboutFragmentListener mListener;

    public AboutAdapter(ReferencesList referencesList, AboutFragment.AboutFragmentListener listener) {
        mReferencesTotal = referencesList.getReferenceCount();
        mReferencesList = referencesList;
        mListener = listener;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AboutListItemView view = new AboutListItemView(parent.getContext());
        return new AboutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutViewHolder holder, final int position) {
        holder.mAboutListItemView.setContents(mReferencesList.getReferenceObject(position), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.followLink(mReferencesList.getReferenceObject(position).getReferenceLink());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (!mReferencesList.isEmpty()) {
            return mReferencesTotal;
        }
        return 0;
    }
}
