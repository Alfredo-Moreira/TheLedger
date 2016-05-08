package com.apolloapps.theledger.DataManager.Responses;

import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 5/7/16.
 */
public class UserCreateAccountResponse {
    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("UserId")
    private int mId;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }
}
