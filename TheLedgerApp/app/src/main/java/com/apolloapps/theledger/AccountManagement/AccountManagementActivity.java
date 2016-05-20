package com.apolloapps.theledger.AccountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.Common.NetworkConstants;
import com.apolloapps.theledger.Dashboard.DashboardActivity;
import com.apolloapps.theledger.DataManager.DataManager;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.DataManager.Utilities.UrlConstructor;
import com.apolloapps.theledger.MainApplication;
import com.apolloapps.theledger.Preferences.Preferences;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.SessionStorage;

/**
 * Created by AMoreira on 4/11/16.
 */
public class AccountManagementActivity extends BaseActivity implements CreateAccountFragment.CreateAccountFragmentListener {

    private Menu mMenu;
    private CreateAccountFragment mCreateAccountFragment;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUp();
        //select fragment
        if (mBundle.getInt(AppConstants.ACTION, 0) == AppConstants.CREATE_ACCOUNT) {
            getFragmentManager().beginTransaction().replace(R.id.container, mCreateAccountFragment).addToBackStack(null).commit();
        } else if (mBundle.getInt(AppConstants.ACTION, 0) == AppConstants.EDIT_ACCOUNT) {
            super.onCreateOptionsMenu(mMenu);
        } else {
            throw new RuntimeException(getString(R.string.account_management_error));
        }
        mBundle.clear();
    }

    private void setUp(){
        mCreateAccountFragment = CreateAccountFragment.newInstance();
        setUpToolBar(getToolBar(), true, true);
        setUpLowerMenu(getLowerMenu(), false);
        mDataManager = new DataManager(this, new UrlConstructor(), Preferences.INSTANCE);
        mBundle = getIntent().getExtras();
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
    public void login() {
        createSession();
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
