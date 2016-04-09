package com.apolloapps.theledger.Preferences;

import android.content.Context;

/**
 * Created by AMoreira on 4/7/16.
 */
public enum Preferences {
    INSTANCE;

    private final String MY_PREFERENCES = "TheLedgerPreferences";
    private final String REMEMBER_ME = "RememberMe";
    private final String USERNAME = "Username";
    private PreferenceHelper mPreferenceHelper;

    public void createPreferences(Context context) {
        mPreferenceHelper = new PreferenceHelper(context, MY_PREFERENCES);
    }

    public void saveRememberMeState(boolean rememberMe) {
        mPreferenceHelper.setBoolean(REMEMBER_ME, rememberMe);
    }

    public void saveUsername(String username) {
        mPreferenceHelper.setString(USERNAME, username);
    }

    public boolean getRememberMeState() {
        return mPreferenceHelper.getBoolean(REMEMBER_ME);
    }

    public String getUsername() {
        return mPreferenceHelper.getString(USERNAME);
    }


}
