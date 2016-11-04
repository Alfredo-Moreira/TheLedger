package com.apolloapps.theledger.AccountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Dashboard.DashboardActivity;
import com.apolloapps.theledger.DataManager.DataManager;
import com.apolloapps.theledger.DataManager.Utilities.UrlConstructor;
import com.apolloapps.theledger.Preferences.Preferences;
import com.apolloapps.theledger.R;

/**
 * Created by AMoreira on 4/11/16.
 */
public class AccountManagementActivity extends BaseActivity implements CreateEditAccountFragment.CreateAccountFragmentListener,
        ViewProfileFragment.ViewProfileFragmentListener {

    private Menu mMenu;
    private CreateEditAccountFragment mCreateEditAccountFragment;



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
        if (getIntent().getExtras() == null) {
            viewProfile();
        } else {
            editProfile();
        }
    }

    private void setUp(){
        mCreateEditAccountFragment = CreateEditAccountFragment.newInstance();
        setUpToolBar(getToolBar(), true, true);
        setUpLowerMenu(getLowerMenu(), false);
        mDataManager = new DataManager(this, new UrlConstructor(), Preferences.INSTANCE);
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
    public void login(int userId) {
        createSession();
        setUserId(userId);
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void viewProfile() {
        setLowerMenu();
        getFragmentManager().beginTransaction().replace(R.id.container, ViewProfileFragment.newInstance(), null).commit();
    }

    @Override
    public void editProfile() {
        setUpLowerMenu(getLowerMenu(), false);
        getFragmentManager().beginTransaction().replace(R.id.container, CreateEditAccountFragment.newInstance(), null).addToBackStack(null).commit();
    }

    @Override
    public void setLowerMenu() {
        setUpLowerMenu(getLowerMenu(), true);
    }

}
