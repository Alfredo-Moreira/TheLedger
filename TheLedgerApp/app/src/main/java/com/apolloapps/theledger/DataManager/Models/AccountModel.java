package com.apolloapps.theledger.DataManager.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 4/8/16.
 */
public class AccountModel {

    @SerializedName("AccountID")
    private int mAccountId;

    @SerializedName("AccountName")
    private String mAccountName;

    @SerializedName("AccountPassword")
    private String mAccountPassword;

    @SerializedName("AccountType")
    private int mAccountType;

    @SerializedName("AccountComments")
    private String mAccountComments;

    public String getAccountComments() {
        return mAccountComments;
    }

    public void setmccountComments(String mAccountComments) {
        this.mAccountComments = mAccountComments;
    }

    public int getAccountId() {
        return mAccountId;
    }

    public void setAccountId(int mAccountId) {
        this.mAccountId = mAccountId;
    }

    public String getAccountName() {
        return mAccountName;
    }

    public void setAccountName(String mAccountName) {
        this.mAccountName = mAccountName;
    }

    public String getAccountPassword() {
        return mAccountPassword;
    }

    public void setAccountPassword(String mAccountPassword) {
        this.mAccountPassword = mAccountPassword;
    }

    public int getAccountType() {
        return mAccountType;
    }

    public void setAccountType(int mAccountType) {
        this.mAccountType = mAccountType;
    }
}
