package com.apolloapps.theledger.DataManager.Responses;

import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AMoreira on 6/30/16.
 */
public class BillGetListResponse {
    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("Data")
    private List<BillModel> mData;

    public List<BillModel> getData() {
        return mData;
    }

    public void setData(List<BillModel> mData) {
        this.mData = mData;
    }

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
