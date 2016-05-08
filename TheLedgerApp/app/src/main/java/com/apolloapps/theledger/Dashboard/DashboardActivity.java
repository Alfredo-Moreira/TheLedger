package com.apolloapps.theledger.Dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.apolloapps.theledger.BaseActivity;
import com.apolloapps.theledger.BaseFragment;
import com.apolloapps.theledger.Login.LoginActivity;
import com.apolloapps.theledger.R;
import com.apolloapps.theledger.Utils.AlertDialogCreator;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by AMoreira on 4/6/16.
 */
public class DashboardActivity extends BaseActivity implements DashboardFragment.DashBoardFragmentListener,
        DialogInterface.OnClickListener, DialogInterface.OnCancelListener {

    private DashboardFragment mDashboardFragment;

    @Override
    public void onBackPressed() {
        if(mDashboardFragment.isFloatingMenuOpen()) {
            mDashboardFragment.closeFloatingMenu(true);
        } else {
            AlertDialogCreator.showDefaultDialog(this, getString(R.string.sign_out), getString(R.string.sign_out_message), this, this, null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_actionbar);
        setUpToolBar(getToolBar(), false, false);
        setUpLowerMenu(getLowerMenu(), true);
        mDashboardFragment = new DashboardFragment().newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, mDashboardFragment, null).commit();
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
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            case BUTTON_POSITIVE:
                //clear session variables TODO
                backToLogin();
                break;
        }
    }

    @Override
    public void startFeatureCreate(String classPath, String action, int value) {

       try {
          mBundle = new Bundle();
           mBundle.putInt(action,value);
         startActivity(new Intent(this, Class.forName(classPath)).putExtras(mBundle));
        } catch (ClassNotFoundException e) {
        AlertDialogCreator.showDefaultDialog(this, getString(R.string.dialog_error), getString(R.string.feature_not_available),null,null,this);
       }
    }

    @Override
    public void startFeature(String classPath) {
        try {
            startActivity(new Intent(this, Class.forName(classPath)));
        } catch (ClassNotFoundException e) {
            AlertDialogCreator.showDefaultDialog(this, getString(R.string.dialog_error), getString(R.string.feature_not_available),null,null,this);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dialog.dismiss();
    }

    private void backToLogin() {
        startActivity(new Intent(this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
