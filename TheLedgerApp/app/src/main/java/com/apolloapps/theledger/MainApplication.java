package com.apolloapps.theledger;

import android.support.multidex.MultiDexApplication;

import com.apolloapps.theledger.Preferences.Preferences;

/**
 * Created by AMoreira on 4/4/16.
 */
public class MainApplication extends MultiDexApplication {

    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        setUp();
    }


    private static void setMainApplication(MainApplication mainApplication) {
        mInstance = mainApplication;
    }

    public MainApplication getMainApplication() {
        return mInstance;
    }

    private void setUp() {
        setMainApplication(MainApplication.this);
        Preferences.INSTANCE.createPreferences(MainApplication.this);
    }

}
