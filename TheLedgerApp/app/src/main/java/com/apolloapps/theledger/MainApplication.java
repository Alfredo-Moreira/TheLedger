package com.apolloapps.theledger;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.apolloapps.theledger.Preferences.Preferences;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AMoreira on 4/4/16.
 */
public class MainApplication extends MultiDexApplication {

    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setUp();
    }


    public MainApplication getMainApplication() {
        return mInstance;
    }

    private static void setMainApplication(MainApplication mainApplication) {
        mInstance = mainApplication;
    }

    private void setUp() {
        setMainApplication(MainApplication.this);
        Preferences.INSTANCE.createPreferences(getApplicationContext());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.regular_font))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}   
