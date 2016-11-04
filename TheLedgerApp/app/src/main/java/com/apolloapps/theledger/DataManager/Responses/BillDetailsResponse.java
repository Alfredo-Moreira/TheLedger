package com.apolloapps.theledger.DataManager.Responses;

import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 7/14/16.
 */
public class BillDetailsResponse {
    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("Data")
    private BillModel mData;


    public int getCode() {
        return mCode;
    }

    public BillModel getData() {
        return mData;
    }

    public void setData(BillModel mData) {
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

