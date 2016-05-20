package com.apolloapps.theledger.DataManager.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 4/8/16.
 */
public class BillModel {

    @SerializedName("BillID")
    private int mBillId;

    @SerializedName("BillName")
    private String mBillName;

    @SerializedName("BillDueDate")
    private String mBillDueDate;

    @SerializedName("BillComments")
    private String mBillComments;

    public String getBillComments() {
        return mBillComments;
    }

    public void setBBillComments(String mBillComments) {
        this.mBillComments = mBillComments;
    }

    public String getBillDueDate() {
        return mBillDueDate;
    }

    public void setBillDueDate(String mBillDueDate) {
        this.mBillDueDate = mBillDueDate;
    }

    public int getBBillId() {
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
}
