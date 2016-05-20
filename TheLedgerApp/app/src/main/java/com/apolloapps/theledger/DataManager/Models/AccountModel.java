package com.apolloapps.theledger.DataManager.Models;

import com.apolloapps.theledger.Common.AppConstants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 4/8/16.
 */
public class AccountModel {

    @SerializedName("accountUserId")
    private int mUserId;

    @SerializedName("_id")
    private int mAccountId;

    @SerializedName("accountTitle")
    private String mAccountTitle;

    @SerializedName("accountUsername")
    private String mAccountUsername;

    @SerializedName("accountPassword")
    private String mAccountPassword;

    @SerializedName("accountType")
    private int mAccountType;

    @SerializedName("accountComments")
    private String mAccountComments;

    public String getAccountComments() {
        return mAccountComments;
    }

    public void setAccountComments(String mAccountComments) {
        this.mAccountComments = mAccountComments;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public int getAccountId() {
        return mAccountId;
    }

    public void setAccountId(int mAccountId) {
        this.mAccountId = mAccountId;
    }

    public String getAccountTitle() {
        return mAccountTitle;
    }

    public void setAccountTitle(String mAccountTitle) {
        this.mAccountTitle = mAccountTitle;
    }

    public String getAccountUsername() {
        return mAccountUsername;
    }

    public void setmAccountUsername(String mAccountUsername) {
        this.mAccountUsername = mAccountUsername;
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

    public String getAccountTypeString() {
        switch (getAccountType()) {
            case AppConstants.FINANCIAL_ACCOUNT_TYPE:
                return "Financial";
            case AppConstants.SOCIAL_ACCOUNT_TYPE:
                return "Social";
            case AppConstants.ENTERTAINMENT_ACCOUNT_TYPE:
                return "Entertainment";
            default:
                return "No Type";
        }
    }
}
