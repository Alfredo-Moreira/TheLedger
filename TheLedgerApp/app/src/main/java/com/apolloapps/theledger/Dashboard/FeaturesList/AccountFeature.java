package com.apolloapps.theledger.Dashboard.FeaturesList;

import android.content.Context;

import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/8/16.
 */
public class AccountFeature extends FeatureBaseClass {


    @Override
    public void init(Context context) {
        mClass = context.getResources().getString(R.string.account_class_path);
        mFeatureName = context.getResources().getString(R.string.account_feature);
        mFeatureDrawableId = R.drawable.account_drawable;

    }

    @Override
    public String getFeatureName() {
        return mFeatureName;
    }

    @Override
    public int getFeatureDrawableId() {
        return mFeatureDrawableId;
    }

    @Override
    public String getFeatureClass() {
        return mClass;
    }
}
