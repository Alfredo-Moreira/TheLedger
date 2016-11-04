package com.apolloapps.theledger.Features.Accounts;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.Features.OverFlowMenu;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/21/16.
 */
public class AccountsActivity extends BaseActivity implements AccountsListFragment.AccountsListFragmentListener,OverFlowMenu.OverFlowMenuListener,
        AccountsDetailsFragment.AccountDetailsFragmentListener, AccountsCreateEditFragment.AccountsCreateFragmentListener {

    private AccountsListFragment mAccountListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(),true,true);
        setUpLowerMenu(getLowerMenu(),false);
        if (getIntent().getExtras() == null) {
            viewAccountList();
        } else {
            createAccountFragmentFromFloat();
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
                editAccountDetails(accountId);
                break;
            default:
                break;
        }
    }



    @Override
    public void deleteFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_ACCOUNT:
                deleteOverflowAccount(accountID);
                break;
            default:
                break;
        }
    }

    @Override
    public void viewAccountDetails(int accountId) {
        getFragmentManager().beginTransaction().replace(R.id.container,AccountsDetailsFragment.newInstance(accountId),null).addToBackStack(null).commit();
    }

    private void viewAccountList() {
        mAccountListFragment = AccountsListFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, mAccountListFragment, null).commit();
    }

    @Override
    public void editAccountDetails(int accountId) {
        getFragmentManager().beginTransaction().replace(R.id.container, AccountsCreateEditFragment.newInstance(true, accountId), null).addToBackStack(null).commit();
    }

    @Override
    public void deleteAccount(int accountId) {
        final AccountsDetailsFragment fragment = (AccountsDetailsFragment) getFragmentManager().findFragmentById(R.id.container);
        mDataManager.doDeleteAccount(accountId, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                fragment.dismissProgressBar();
                fragment.showMainScreen();
                onBackPressed();
            }

            @Override
            public void onError(NetworkError error) {
                fragment.dismissProgressBar();
                fragment.showCorrectErrorScreen(error.getStatusCode());

            }

            @Override
            public void onPreExecute() {
                fragment.hideAllScreens();
                fragment.showProgressBar();
            }
        });
    }

    @Override
    public void editAccount(int accountId) {
        editAccountDetails(accountId);
    }

    public void deleteOverflowAccount(int accountId) {
        final AccountsListFragment fragment = (AccountsListFragment) getFragmentManager().findFragmentById(R.id.container);
        mDataManager.doDeleteAccount(accountId, new ServiceCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                fragment.dismissProgressBar();
                fragment.showMainScreen();
                fragment.getAccountsList();
            }

            @Override
            public void onError(NetworkError error) {
                fragment.dismissProgressBar();
                fragment.showCorrectErrorScreen(error.getStatusCode());

            }

            @Override
            public void onPreExecute() {
                fragment.hideAllScreens();
                fragment.showProgressBar();
            }
        });
    }

    @Override
    public void peakAccount(AccountModel accountModel) {
     mAccountListFragment.peakAccountDetails(accountModel);
    }

    @Override
    public void createAccountFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, AccountsCreateEditFragment.newInstance(false, AppConstants.NO_ACCOUNT), null).addToBackStack(null).commit();
    }

    public void createAccountFragmentFromFloat() {
        getFragmentManager().beginTransaction().replace(R.id.container, AccountsCreateEditFragment.newInstance(false, AppConstants.NO_ACCOUNT), null).commit();

    }


    @Override
    public void returnToAccountList() {
        super.onBackPressed();
    }
}
