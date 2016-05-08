package com.apolloapps.theledger.Features.Accounts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.Features.OverFlowMenu;
import com.apolloapps.theledger.MainApplication;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/21/16.
 */
public class AccountsActivity extends BaseActivity implements AccountsListFragment.AccountsListFragmentListener,OverFlowMenu.OverFlowMenuListener,
        AccountsDetailsFragment.AccountDetailsFragmentListener,AccountsCreateFragment.AccountsCreateFragmentListener {

    private AccountsListFragment mAccountListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"User login id is "+ MainApplication.getMainApplication().mSessionStorage.getUserId(),Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(),true,true);
        setUpLowerMenu(getLowerMenu(),false);
        mAccountListFragment = AccountsListFragment.newInstance();
        mBundle = getIntent().getExtras();
        if(mBundle!=null && mBundle.getInt("Create Account") == 1) {
            getFragmentManager().beginTransaction().replace(R.id.container, AccountsCreateFragment.newInstance(), null).addToBackStack(null).commit();
        } else {
            getFragmentManager().beginTransaction().replace(R.id.container, mAccountListFragment, null).addToBackStack(null).commit();
        }
    }

    @Override
    public void viewFeature(int featureType, int accountID) {
        switch (featureType) {
            case AppConstants.FEATURE_TYPE_ACCOUNT:
                Toast.makeText(this,"View Feature",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    @Override
    public void editFeature(int featureType, int accountID) {
        Toast.makeText(this,"Edit Feature",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteFeature(int featureType, int accountID) {
        Toast.makeText(this,"Delete Feature",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void viewAccountDetails(AccountModel accountModel) {
        getFragmentManager().beginTransaction().replace(R.id.container,AccountsDetailsFragment.newInstance(accountModel),null).addToBackStack(null).commit();
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
    public void createAccount() {
        getFragmentManager().beginTransaction().replace(R.id.container,AccountsCreateFragment.newInstance(),null).addToBackStack(null).commit();
    }



}
