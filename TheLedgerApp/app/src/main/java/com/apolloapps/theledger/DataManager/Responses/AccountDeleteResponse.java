package com.apolloapps.theledger.DataManager.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 5/28/16.
 */
public class AccountDeleteResponse {

    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }
}
