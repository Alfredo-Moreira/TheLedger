package com.apolloapps.theledger.Common;

import android.content.Context;

import com.apolloapps.theledger.DataManager.Models.ReferenceModel;
import com.apolloapps.theledger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMoreira on 4/13/16.
 */
public class ReferencesList {
    private List<ReferenceModel> mReferencesList;
    private int mReferenceCount;

    public ReferencesList(Context context) {
        mReferencesList = new ArrayList<>();
        mReferenceCount = context.getResources().getInteger(R.integer.references_count);
        addAllReferences(context);
    }


    private void addAllReferences(Context context) {
        mReferencesList.add(new ReferenceModel(context.getString(R.string.butter_title),
                context.getString(R.string.butter_link),
                context.getString(R.string.butter_descriptions)));
        mReferencesList.add(new ReferenceModel(context.getString(R.string.gson_title),
                context.getString(R.string.gson_link),
                context.getString(R.string.gson_descriptions)));
        mReferencesList.add(new ReferenceModel(context.getString(R.string.calligraphy_title),
                context.getString(R.string.calligraphy_link),
                context.getString(R.string.calligraphy_description)));
    }

    public ReferenceModel getReferenceObject(int position) {
        return mReferencesList.get(position);
    }

    public int getReferenceCount() {
        return mReferenceCount;
    }

    public boolean isEmpty() {
        return mReferencesList.isEmpty();
    }


}
