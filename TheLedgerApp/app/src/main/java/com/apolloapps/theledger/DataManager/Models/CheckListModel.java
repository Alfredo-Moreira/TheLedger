package com.apolloapps.theledger.DataManager.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 9/28/16.
 */
public class CheckListModel {

    @SerializedName("accountUserId")
    private int mUserId;

    @SerializedName("_id")
    private int mCheckListId;

    @SerializedName("checkListTitle")
    private String mCheckListTitle;

    @SerializedName("checkListChecked")
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getCheckListId() {
        return mCheckListId;
    }

    public void setCheckListId(int mCheckListId) {
        this.mCheckListId = mCheckListId;
    }

    public String getCheckListTitle() {
        return mCheckListTitle;
    }

    public void setCheckListTitle(String mCheckListTitle) {
        this.mCheckListTitle = mCheckListTitle;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }
}
