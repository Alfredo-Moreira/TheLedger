package com.apolloapps.theledger.Features.Accounts;

import android.content.Context;

import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMoreira on 5/3/16.
 */
public class AccountTypes {

    protected List<AccountType> mAccountTypeList;

    public List<AccountType> AccountTypes(Context context) {
        setAccountTypeList(context);
        return mAccountTypeList;
    }

    private void setAccountTypeList(Context context){
        mAccountTypeList = new ArrayList<>();
        mAccountTypeList.add(new AccountType(AppConstants.SOCIAL_ACCOUNT_TYPE,context.getResources().getString(R.string.social)));
        mAccountTypeList.add(new AccountType(AppConstants.FINANCIAL_ACCOUNT_TYPE,context.getResources().getString(R.string.financial)));
        mAccountTypeList.add(new AccountType(AppConstants.ENTERTAINMENT_ACCOUNT_TYPE,context.getResources().getString(R.string.entertainment)));
    }
}


class AccountType {
    private int mAccountTypeId;
    private String mAccountType;

    AccountType(int accountTypeId,String accountType) {
        mAccountTypeId = accountTypeId;
        mAccountType = accountType;
    }

    public String getAccountType() {
        return mAccountType;
    }

    public int getAccountTypeId() {
        return mAccountTypeId;
    }
}