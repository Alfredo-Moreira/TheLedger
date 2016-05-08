package com.apolloapps.theledger.DataManager.Responses;

import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 5/8/16.
 */
public class UserGetDetailsResponse {

    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("Data")
    private PersonalAccountModel mData;


    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public PersonalAccountModel getData() {
        return mData;
    }

    public void setData(PersonalAccountModel mData) {
        this.mData = mData;
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
