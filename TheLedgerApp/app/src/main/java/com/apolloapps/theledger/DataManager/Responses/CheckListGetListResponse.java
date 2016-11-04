package com.apolloapps.theledger.DataManager.Responses;

import com.apolloapps.theledger.DataManager.Models.CheckListModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AMoreira on 9/30/16.
 */
public class CheckListGetListResponse {
    @SerializedName("Status")
    private int mStatus;

    @SerializedName("Code")
    private int mCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("Data")
    private List<CheckListModel> mData;

    public List<CheckListModel> getData() {
        return mData;
    }

    public void setData(List<CheckListModel> mData) {
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
