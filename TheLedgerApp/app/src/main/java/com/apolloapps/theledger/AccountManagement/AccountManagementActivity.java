package com.apolloapps.theledger.AccountManagement;

import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.AppConstants;

/**
 * Created by AMoreira on 4/11/16.
 */
public class AccountManagementActivity extends BaseActivity implements CreateAccountFragment.CreateAccountFragmentListener {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(R.id.toolbar), getString(R.string.create_account_action_bar_title), true);
        setLowerMenuVisibility(getLowerMenu(R.id.secondary_menus_container), false);

        //select fragment
        if(savedInstanceState.getInt(AppConstants.ACTION,0) == AppConstants.CREATE_ACCOUNT) {
            getFragmentManager().beginTransaction().replace(R.id.container, CreateAccountFragment.newInstance()).addToBackStack(null).commit();
        } else if (savedInstanceState.getInt(AppConstants.ACTION,0) == AppConstants.EDIT_ACCOUNT) {
            getFragmentManager().beginTransaction().replace(R.id.container, EditAccountFragment.newInstance()).addToBackStack(null).commit();
        } else {
            throw new RuntimeException("Need select create account or edit account");
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
