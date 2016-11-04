package com.apolloapps.theledger.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.apolloapps.theledger.AccountManagement.AccountManagementActivity;
import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.Common.NetworkConstants;
import com.apolloapps.theledger.Dashboard.DashboardActivity;
import com.apolloapps.theledger.DataManager.Responses.LoginResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.Preferences.Preferences;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.AlertDialogCreator;
import com.apolloapps.theledger.Utils.ProgressDialogCreator;


/**
 * Created by AMoreira on 4/5/16.
 */
public class LoginActivity extends BaseActivity implements LoginFragment.LoginFragmentListener {

    protected ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_no_actionbar);
        startLoginFragment();
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
    public void signIn(String username, String password, final boolean rememberMe) {

        savePreferences(username, rememberMe);
        mDataManager.doLogin(username, password, new ServiceCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                ProgressDialogCreator.dismissDialog(mDialog);
                createSession();
                setUserId(response.getUserId());
                startDashboard();
            }

            @Override
            public void onError(NetworkError error) {
                ProgressDialogCreator.dismissDialog(mDialog);
                if (error.getStatusCode() == NetworkConstants.STATUS_403) {
                    showWrongCredentials();
                } else if(error.getStatusCode() == NetworkConstants.STATUS_0) {
                    showNoNetworkError();
                } else {
                    showServerError();
                }

            }

            @Override
            public void onPreExecute() {
                mDialog = ProgressDialogCreator.showProgressDialog(getActivityContext());
            }
        });

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

    private void startLoginFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, LoginFragment.newInstance()).addToBackStack(null).commit();
    }

    private void savePreferences(String username, boolean rememberMe) {
        Preferences.INSTANCE.saveRememberMeState(rememberMe);
        if (rememberMe) {
            Preferences.INSTANCE.saveUsername(username);
        } else {
            Preferences.INSTANCE.saveUsername(null);
        }
    }

    private void startDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void showWrongCredentials(){
        AlertDialogCreator.showDefaultDialog(getActivityContext(), getString(R.string.error),
                getActivityContext().getString(R.string.invalid_username_password),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, null, null);
    }
    private void showServerError(){
        AlertDialogCreator.showDefaultDialog(getActivityContext(), getString(R.string.error),
                getActivityContext().getString(R.string.server_error),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, null, null);
    }
    private void showNoNetworkError() {
        AlertDialogCreator.showDefaultDialog(getActivityContext(), getString(R.string.error),
                getString(R.string.no_network_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },null,null);
    }


}
