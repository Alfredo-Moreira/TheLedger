package com.apolloapps.theledger.DataManager.Responses;

import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AMoreira on 5/10/16.
 */
public class AccountGetDetailsResponse {

    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("Data")
    private AccountModel mData;


    public int getCode() {
        return mCode;
    }

    public AccountModel getData() {
        return mData;
    }

    public void setData(AccountModel mData) {
        this.mData = mData;
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
