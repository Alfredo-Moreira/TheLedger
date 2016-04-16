package com.apolloapps.theledger.AccountManagement;

import android.os.Bundle;
import android.view.Menu;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/11/16.
 */
public class AccountManagementActivity extends BaseActivity implements CreateAccountFragment.CreateAccountFragmentListener {

    private Menu mMenu;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //do nothing
        mMenu = menu;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(), getString(R.string.create_account_action_bar_title), true, true);
        setUpLowerMenu(getLowerMenu(), false);
        mBundle = getIntent().getExtras();
        //select fragment
        if (mBundle.getInt(AppConstants.ACTION, 0) == AppConstants.CREATE_ACCOUNT) {
            getFragmentManager().beginTransaction().replace(R.id.container, CreateAccountFragment.newInstance()).addToBackStack(null).commit();
        } else if (mBundle.getInt(AppConstants.ACTION, 0) == AppConstants.EDIT_ACCOUNT) {
            getFragmentManager().beginTransaction().replace(R.id.container, EditAccountFragment.newInstance()).addToBackStack(null).commit();
            super.onCreateOptionsMenu(mMenu);
        } else {
            throw new RuntimeException(getString(R.string.account_management_error));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void createAccount(PersonalAccountModel account) {
        //retrieve info from account and do network request

    }
}
