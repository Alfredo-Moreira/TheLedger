package com.apolloapps.theledger.Dashboard.Features;

import android.content.Context;

/**
 * Created by AMoreira on 4/8/16.
 */
public abstract class FeatureBaseClass {

    public int mFeatureDrawableId;
    public String mFeatureName;
    public String mClass;

    public abstract void init(Context context);
    public abstract String getFeatureName();
    public abstract int getFeatureDrawableId();
    public abstract String getFeatureClass();
}
