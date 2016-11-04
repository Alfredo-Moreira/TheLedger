package com.apolloapps.theledger;


import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.Preferences.Preferences;

/**
 * Created by AMoreira on 5/8/16.
 */
public class SessionStorage {

    private int mUserId = AppConstants.NO_ACCOUNT;


    public SessionStorage() {
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }


    public void clearSession() {
        mUserId = AppConstants.NO_ACCOUNT;
        Preferences.INSTANCE.saveRememberMeState(false);
    }
}
