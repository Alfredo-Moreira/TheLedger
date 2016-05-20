package com.apolloapps.theledger.Features.Accounts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.Features.OverFlowMenu;
import com.apolloapps.theledger.MainApplication;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/21/16.
 */
public class AccountsActivity extends BaseActivity implements AccountsListFragment.AccountsListFragmentListener,OverFlowMenu.OverFlowMenuListener,
        AccountsDetailsFragment.AccountDetailsFragmentListener,AccountsCreateFragment.AccountsCreateFragmentListener {

    private AccountsListFragment mAccountListFragment;
    private AccountModel mAccountModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"User login id is "+ getUserId(),Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(),true,true);
        setUpLowerMenu(getLowerMenu(),false);
        mAccountListFragment = AccountsListFragment.newInstance();
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            if (mBundle.getInt(AppConstants.ACTION) == AppConstants.CREATE_ACCOUNT) {
                getFragmentManager().beginTransaction().replace(R.id.container, AccountsCreateFragment.newInstance(null), null).addToBackStack(null).commit();
            } else {
                getFragmentManager().beginTransaction().replace(R.id.container, mAccountListFragment, null).addToBackStack(null).commit();
            }
        } else {
            throw new RuntimeException(this.getString(R.string.no_bundle_attached));
        }
    }

    @Override
    public void viewFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_ACCOUNT:
                viewAccountDetails(accountID);
                break;
            default:
                break;
        }

    }

    @Override
    public void editFeature(int featureType, int accountId) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_ACCOUNT:
                editAccountDetails(getAccountModel(accountId));
                break;
            default:
                break;
        }
    }

    private AccountModel getAccountModel(int id) {

        mDataManager.doGetAccountDetails(getUserId(), id, new ServiceCallback<AccountGetDetailsResponse>() {
            @Override
            public void onSuccess(AccountGetDetailsResponse response) {
                mAccountListFragment.dismissProgressBar();
                mAccountModel = new AccountModel();
            }

            @Override
            public void onError(NetworkError error) {
                mAccountListFragment.dismissProgressBar();
              mAccountListFragment.showCorrectErrorScreen(error.getStatusCode());
            }

            @Override
            public void onPreExecute() {
                mAccountListFragment.hideAllScreens();
                mAccountListFragment.showProgressBar();
            }
        });
        return mAccountModel;
    }

    @Override
    public void deleteFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_ACCOUNT:
                deleteAccount(accountID);
                break;
            default:
                break;
        }
    }

    @Override
    public void viewAccountDetails(int accountId) {
        getFragmentManager().beginTransaction().replace(R.id.container,AccountsDetailsFragment.newInstance(accountId),null).addToBackStack(null).commit();
    }

    @Override
    public void editAccountDetails(AccountModel model) {
        getFragmentManager().beginTransaction().replace(R.id.container,AccountsCreateFragment.newInstance(model),null).addToBackStack(null).commit();
    }

    @Override
    public void deleteAccount(int accountId) {

    }

    @Override
    public void peakAccount(AccountModel accountModel) {
     mAccountListFragment.peakAccountDetails(accountModel);
    }

    @Override
    public void hidePeakAccount() {
        mAccountListFragment.hidePeakAccountDetails();
    }


    @Override
    public void createAccountFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container,AccountsCreateFragment.newInstance(null),null).addToBackStack(null).commit();
    }

    @Override
    public void returnToAccountList() {
        super.onBackPressed();
    }
}
