package com.apolloapps.theledger.Dashboard.Features;

import android.content.Context;

import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/9/16.
 */
public class CheckListFeature extends FeatureBaseClass {
    @Override
    public void init(Context context) {
        mClass = context.getString(R.string.check_list_class_path);
        mFeatureDrawableId = R.drawable.chrck_list_drawable;
        mFeatureName = context.getString(R.string.check_list_feature);
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
