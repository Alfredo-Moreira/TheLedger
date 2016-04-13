package com.apolloapps.theledger.Login;

import android.content.Intent;
import android.os.Bundle;

import com.apolloapps.theledger.AccountManagement.AccountManagementActivity;
import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Dashboard.DashboardActivity;
import com.apolloapps.theledger.Preferences.Preferences;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.AppConstants;


/**
 * Created by AMoreira on 4/5/16.
 */
public class LoginActivity extends BaseActivity implements LoginFragment.LoginFragmentListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_no_actionbar);
        getFragmentManager().beginTransaction().replace(R.id.container, LoginFragment.newInstance()).addToBackStack(null).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void signIn(String username, String password, boolean rememberMe) {
        //To be replaced with Actual Login flow
        savePreferences(username, rememberMe);
        startActivity(new Intent(this, DashboardActivity.class));
    }

    @Override
    public void createAccount() {
        mBundle = new Bundle();
        mBundle.putInt(AppConstants.ACTION, AppConstants.CREATE_ACCOUNT);
        startActivity(new Intent(this, AccountManagementActivity.class).putExtras(mBundle));


    }

    @Override
    public void forgotCredentials() {
        //startActivity(new Intent(this, DashboardActivity.class));

    }

    private void savePreferences(String username, boolean rememberMe) {
        Preferences.INSTANCE.saveRememberMeState(rememberMe);
        if (rememberMe) {
            Preferences.INSTANCE.saveUsername(username);
        } else {
            Preferences.INSTANCE.saveUsername(null);
        }
    }


}
