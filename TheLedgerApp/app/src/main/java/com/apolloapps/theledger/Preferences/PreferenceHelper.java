package com.apolloapps.theledger.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.apolloapps.theledger.Utils.AppConstants;

/**
 * Created by AMoreira on 4/7/16.
 */
public class PreferenceHelper {

    private SharedPreferences mPreferences;

    public PreferenceHelper(Context context,String preferences) {
        mPreferences = context.getSharedPreferences(preferences,context.MODE_PRIVATE);
    }

    protected void setBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key,value);
        mPreferences.edit().commit();
    }

    protected void setString(String key, String value) {
        mPreferences.edit().putString(key, value);
        mPreferences.edit().commit();
    }

    protected void setInt(String key,int value) {
        mPreferences.edit().putInt(key, value);
        mPreferences.edit().commit();
    }

    protected boolean getBoolean(String key) {
       return mPreferences.getBoolean(key,false);
    }

    protected String getString(String key) {
        return mPreferences.getString(key, null);
    }

    protected int getInt(String key) {
        return mPreferences.getInt(key, AppConstants.DEFAULT_INT_RETURN);
    }



}
