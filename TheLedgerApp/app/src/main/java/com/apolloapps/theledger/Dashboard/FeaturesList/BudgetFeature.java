package com.apolloapps.theledger.Dashboard.FeaturesList;

import android.content.Context;

import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 6/21/16.
 */
public class BudgetFeature extends FeatureBaseClass{

    @Override
    public void init(Context context) {
        mClass = context.getResources().getString(R.string.budget_class_path);
        mFeatureName = context.getResources().getString(R.string.budget_feature);
        mFeatureDrawableId = R.drawable.budget_drawable;

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
