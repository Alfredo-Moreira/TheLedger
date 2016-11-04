package com.apolloapps.theledger.DataManager.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 4/8/16.
 */
public class BillModel {

    @SerializedName("accountUserId")
    private int mUserId;

    @SerializedName("_id")
    private int mBillId;

    @SerializedName("billName")
    private String mBillName;

    @SerializedName("billType")
    private String mBillType;

    @SerializedName("billDueDate")
    private String mBillDueDate;

    @SerializedName("billComments")
    private String mBillComments;

    @SerializedName("billReminderSet")
    private boolean mIsReminderSet;


    @SerializedName("billReminderDate")
    private String mBillReminderDate;

    public String getmBillReminderDate() {
        return mBillReminderDate;
    }

    public void setmBillReminderDate(String mBillReminderDate) {
        this.mBillReminderDate = mBillReminderDate;
    }

    public boolean isIsRemiderSet() {
        return mIsReminderSet;
    }

    public void setIsRemiderSet(boolean mIsRemiderSet) {
        this.mIsReminderSet = mIsRemiderSet;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getBillComments() {
        return mBillComments;
    }

    public void setBillComments(String mBillComments) {
        this.mBillComments = mBillComments;
    }

    public String getBillDueDate() {
        return mBillDueDate;
    }

    public void setBillDueDate(String mBillDueDate) {
        this.mBillDueDate = mBillDueDate;
    }

    public int getBillId() {
        return mBillId;
    }

    public void setBillId(int mBillId) {
        this.mBillId = mBillId;
    }

    public String getBillName() {
        return mBillName;
    }

    public void setBillName(String mBillName) {
        this.mBillName = mBillName;
    }

    public String getBillType() {
        return mBillType;
    }

    public void setBillType(String mBillType) {
        this.mBillType = mBillType;
    }

    public boolean getIsIsReminderSet() {
        return mIsReminderSet;
    }

    public void setIsReminderSet(boolean mIsReminderSet) {
        this.mIsReminderSet = mIsReminderSet;
    }
}
