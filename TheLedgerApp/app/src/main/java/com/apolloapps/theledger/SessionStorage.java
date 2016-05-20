package com.apolloapps.theledger;


import com.apolloapps.theledger.Preferences.Preferences;

/**
 * Created by AMoreira on 5/8/16.
 */
public class SessionStorage {

    private int mUserId;


    public SessionStorage() {
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }


    public void clearSession() {
        mUserId = -1;
        Preferences.INSTANCE.saveRememberMeState(false);
    }
}
